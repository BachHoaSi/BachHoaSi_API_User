package com.swd391.bachhoasi_user.service.impl;

import org.springframework.stereotype.Service;

import com.swd391.bachhoasi_user.model.dto.response.LoginResponse;
import com.swd391.bachhoasi_user.model.dto.response.ZaloUserResponse;
import com.swd391.bachhoasi_user.repository.StoreRepository;
import com.swd391.bachhoasi_user.service.OauthService;
import com.swd391.bachhoasi_user.service.ZaloApiService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{
    private final ZaloApiService zaloApiService;
    private final StoreRepository storeRepository;
    @Override
    public LoginResponse oauthLogin(String code, String state, String codeChallenge) {
        String accessToken = zaloApiService.getAccessToken(codeChallenge);
        ZaloUserResponse zaloUser = zaloApiService.getZaloUserInformation(accessToken);
        return null;
    }
    
}
