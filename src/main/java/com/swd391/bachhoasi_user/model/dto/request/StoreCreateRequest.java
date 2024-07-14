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
@Schema(description = "Request to create a new store")
public class StoreCreateRequest {

    @NotNull(message = "Store name should not be null")
    @Schema(description = "Name of the store", required = true, example = "Bach Hoa Si Store")
    private String name;

    @NotNull(message = "Store location should not be null")
    @Schema(description = "Location of the store", required = true, example = "123 Main St, City, Country")
    private String location;

    @NotNull(message = "Store phone number should not be null")
    @Pattern(regexp = "\\b(84[35789][0-9]{8})\\b|\\b(0[35789][0-9]{8})\\b", message = "Invalid phone number")
    @Schema(description = "Phone number of the store", required = true, example = "84987654321")
    private String phoneNumber;

    @NotNull(message = "Store zalo id should not be null")
    @Schema(description = "Zalo ID of the store", required = true, example = "zalo12345")
    private String zaloId;

    @NotNull(message = "Store type should not be null")
    @Schema(description = "Type of the store", required = true, example = "1")
    private BigDecimal storeTypeId;
}