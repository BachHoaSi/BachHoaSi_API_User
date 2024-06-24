package com.swd391.bachhoasi_user.service;

import com.swd391.bachhoasi_user.model.dto.response.LoginResponse;

public interface OauthService {
    LoginResponse oauthLogin (String code);
}
