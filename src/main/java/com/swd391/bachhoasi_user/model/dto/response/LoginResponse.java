package com.swd391.bachhoasi_user.model.dto.response;

import com.swd391.bachhoasi_user.model.constant.TokenType;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Access token for authentication", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;
    
    @Schema(description = "Refresh token for obtaining new access tokens", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String refreshToken;
    
    @Schema(description = "ID of the associated store", example = "1234.56")
    private BigDecimal storeId;
    @Schema(description = "Type of token (e.g., Bearer)", required = true)
    private TokenType tokenType;
    public LoginResponse(String accessToken, String refreshToken, BigDecimal storeId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.storeId = storeId;
        this.tokenType = TokenType.BEARER;
    }

}