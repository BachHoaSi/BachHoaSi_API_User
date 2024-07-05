package com.swd391.bachhoasi_user.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Builder
@Data
@AllArgsConstructor
@Schema(description = "Request to delete an item from the cart")
public class CartDeleteRequest {
    @Schema(description = "ID of the item to be deleted", example = "1234")
    private BigDecimal itemId;

    @NotNull(message = "Cart id should not be null")
    @Schema(description = "ID of the cart", example = "5678")
    private BigDecimal cartId;

    @NotNull(message = "Store id should not be null")
    @Schema(description = "ID of the store", example = "987")
    private BigDecimal storeId;
}