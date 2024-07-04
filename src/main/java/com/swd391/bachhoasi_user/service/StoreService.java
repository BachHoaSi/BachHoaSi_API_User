package com.swd391.bachhoasi_user.service;

import com.swd391.bachhoasi_user.model.dto.request.StoreUpdateRequest;
import com.swd391.bachhoasi_user.model.dto.response.StoreDetailsResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface StoreService {

    StoreDetailsResponse getStoreById (BigDecimal id);

    StoreDetailsResponse updateStore (StoreUpdateRequest store);

}