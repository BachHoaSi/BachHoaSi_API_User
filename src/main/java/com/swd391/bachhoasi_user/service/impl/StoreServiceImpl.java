package com.swd391.bachhoasi_user.service.impl;


import com.swd391.bachhoasi_user.model.dto.request.StoreUpdateRequest;
import com.swd391.bachhoasi_user.model.dto.response.StoreDetailsResponse;
import com.swd391.bachhoasi_user.model.entity.Store;
import com.swd391.bachhoasi_user.model.exception.AllNullException;
import com.swd391.bachhoasi_user.model.exception.NotFoundException;
import com.swd391.bachhoasi_user.model.exception.ValidationFailedException;
import com.swd391.bachhoasi_user.repository.StoreRepository;
import com.swd391.bachhoasi_user.service.StoreService;
import com.swd391.bachhoasi_user.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final AuthUtils authUtils;

    private final StoreRepository storeRepository;

    @Override
    public StoreDetailsResponse getStoreById(BigDecimal id) {

        var currentUser = authUtils.getUserFromAuthentication();
        if(!currentUser.getId().equals(id)){
            throw new ValidationFailedException("You are not authorized to access this store");
        }
        Store store = storeRepository.findById(currentUser.getId()).orElseThrow(() -> new NotFoundException("Store is not found!"));
        return convertToStoreDetailsResponse(store);

    }

    @Override
    public StoreDetailsResponse updateStore(StoreUpdateRequest storeUpdate) {

        var currentUser = authUtils.getUserFromAuthentication();
        if(!currentUser.getId().equals(storeUpdate.getId())){
            throw new ValidationFailedException("You are not authorized to access this store");
        }
        Store store = storeRepository.findById(currentUser.getId()).orElseThrow(() -> new NotFoundException("Store is not found!"));
        if(storeUpdate.getName() == null && storeUpdate.getPhoneNumber() == null && storeUpdate.getLocation() == null) {
            throw new AllNullException("All fields are null!");
        }else {
            if (storeUpdate.getName() != null) {
                store.setName(storeUpdate.getName());
            }
            if (storeUpdate.getPhoneNumber() != null) {
                store.setPhoneNumber(storeUpdate.getPhoneNumber());
            }
            if (storeUpdate.getLocation() != null) {
                store.setLocation(storeUpdate.getLocation());
            }
            storeRepository.save(store);
        }
        return convertToStoreDetailsResponse(store);
    }

    private StoreDetailsResponse convertToStoreDetailsResponse(Store store) {
        return StoreDetailsResponse.builder()
                .name(store.getName())
                .storeType(store.getType().getName())
                .point(store.getPoint())
                .phoneNumber(store.getPhoneNumber())
                .location(store.getLocation())
                .storeLevel(store.getStoreLevel().getLevel())
                .build();
    }

}