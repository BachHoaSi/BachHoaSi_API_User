package com.swd391.bachhoasi_user.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response for all store types")
public class StoreTypeResponse {

    @Schema(description = "Id of the store type")
    private BigDecimal id;
    @Schema(description = "Name of the store type")
    private String name;
    @Schema(description = "Description of the store type")
    private String description;


}