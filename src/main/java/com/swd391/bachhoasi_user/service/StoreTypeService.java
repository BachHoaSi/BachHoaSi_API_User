package com.swd391.bachhoasi_user.service;

import com.swd391.bachhoasi_user.model.dto.response.StoreTypeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreTypeService {

    List<StoreTypeResponse> getAllStoreTypes();



}