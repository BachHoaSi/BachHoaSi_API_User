package com.swd391.bachhoasi_user.service;

import com.swd391.bachhoasi_user.model.dto.request.LoginDto;
import com.swd391.bachhoasi_user.model.dto.request.StoreCreateRequest;
import com.swd391.bachhoasi_user.model.dto.response.LoginResponse;

public interface OauthService {
    LoginResponse oauthLogin (LoginDto loginDto);

    boolean oauthSignup (StoreCreateRequest createRequest);

}