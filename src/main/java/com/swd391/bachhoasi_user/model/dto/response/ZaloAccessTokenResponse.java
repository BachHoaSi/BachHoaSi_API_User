package com.swd391.bachhoasi_user.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class ZaloAccessTokenResponse {
    @JsonProperty("access-token")
    private String accessToken;
    @JsonProperty("refresh-token")
    private String refreshToken;
    @JsonProperty("expires-in")
    private Integer expiresIn;
}