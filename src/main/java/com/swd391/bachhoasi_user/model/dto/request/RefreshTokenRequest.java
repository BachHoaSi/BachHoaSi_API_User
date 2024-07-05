package com.swd391.bachhoasi_user.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request to refresh the authentication token")
public class RefreshTokenRequest {
    
    @NotBlank(message = "Refresh token should not be blank")
    @Schema(description = "Refresh token to obtain a new authentication token", required = true, example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String refreshToken;
}
