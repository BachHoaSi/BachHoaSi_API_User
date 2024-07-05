package com.swd391.bachhoasi_user.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Response containing Zalo user details")
public class ZaloUserResponse {

    @Schema(description = "ID of the Zalo user")
    private String id;

    @Schema(description = "Name of the Zalo user")
    private String name;
}
