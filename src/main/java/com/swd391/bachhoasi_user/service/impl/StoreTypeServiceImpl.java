package com.swd391.bachhoasi_user.service.impl;

import com.swd391.bachhoasi_user.model.dto.response.StoreTypeResponse;
import com.swd391.bachhoasi_user.model.entity.StoreType;
import com.swd391.bachhoasi_user.repository.StoreTypeRepository;
import com.swd391.bachhoasi_user.service.StoreTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreTypeServiceImpl implements StoreTypeService {

    private final StoreTypeRepository storeTypeRepository;

    @Override
    public List<StoreTypeResponse> getAllStoreTypes() {
        List<StoreType> storeTypes = storeTypeRepository.findByStatus(true);
        return storeTypes.stream().map(this::convertToStoreTypeResponse).toList();

    }

    private StoreTypeResponse convertToStoreTypeResponse(StoreType storeType) {
        return StoreTypeResponse.builder()
                .id(storeType.getId())
                .name(storeType.getName())
                .build();
    }

}