package com.swd391.bachhoasi_user.model.dto.response;

import lombok.Data;
@Data
public class ZaloAccessTokenResponse {
    private String accessToken;
    private String refreshToken;
    private Integer expiresIn;
}