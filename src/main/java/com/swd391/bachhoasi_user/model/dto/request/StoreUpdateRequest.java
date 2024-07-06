package com.swd391.bachhoasi_user.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Request to update store details")
public class StoreUpdateRequest {

    @NotNull(message = "Store id should not be null")
    @Schema(description = "ID of the store", required = true, example = "1234.56")
    private BigDecimal id;

    @Schema(description = "Name of the store", example = "Bach Hoa Si Store")
    private String name;

    @Pattern(regexp = "\\b(84[35789][0-9]{8})\\b|\\b(0[35789][0-9]{8})\\b", message = "Invalid phone number")
    @Schema(description = "Phone number of the store", example = "84987654321")
    private String phoneNumber;

    @Schema(description = "Location of the store", example = "123 Main St, City, Country")
    private String location;
}
