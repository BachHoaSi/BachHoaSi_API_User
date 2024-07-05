package com.swd391.bachhoasi_user.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Response containing Zalo access token details")
public class ZaloAccessTokenResponse {

    @Schema(description = "Access token for Zalo authentication")
    private String accessToken;

    @Schema(description = "Refresh token for obtaining new Zalo access tokens")
    private String refreshToken;

    @Schema(description = "Time in seconds until the access token expires")
    private Integer expiresIn;
}
