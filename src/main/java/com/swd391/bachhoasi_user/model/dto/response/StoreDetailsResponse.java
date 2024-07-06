package com.swd391.bachhoasi_user.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response representing store details")
public class StoreDetailsResponse {

    @Schema(description = "Name of the store", example = "Bach Hoa Si Store")
    private String name;

    @Schema(description = "Type of the store", example = "Supermarket")
    private String storeType;

    @Schema(description = "Points associated with the store", example = "100")
    private Integer point;

    @Schema(description = "Phone number of the store", example = "84987654321")
    private String phoneNumber;

    @Schema(description = "Location of the store", example = "123 Main St, City, Country")
    private String location;

    @Schema(description = "Level of the store", example = "3")
    private Integer storeLevel;
}
