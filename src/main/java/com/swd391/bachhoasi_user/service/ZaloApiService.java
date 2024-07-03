package com.swd391.bachhoasi_user.service;


import com.swd391.bachhoasi_user.model.dto.response.ZaloUserResponse;

public interface ZaloApiService {
    String getAccessToken(String code);
    ZaloUserResponse getZaloUserInformation(String accessToken);

}
