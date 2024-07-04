package com.swd391.bachhoasi_user.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.swd391.bachhoasi_user.model.constant.Role;
import com.swd391.bachhoasi_user.model.constant.TokenType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    @JsonProperty("access-token")
    private String accessToken;
    @JsonProperty("refresh-token")
    private String refreshToken;
    @JsonProperty("store-id")
    private BigDecimal storeId;
    @JsonProperty("token-type")
    private TokenType tokenType;
    public LoginResponse(String accessToken, String refreshToken, BigDecimal storeId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.storeId = storeId;
        this.tokenType = TokenType.BEARER;
    }

}