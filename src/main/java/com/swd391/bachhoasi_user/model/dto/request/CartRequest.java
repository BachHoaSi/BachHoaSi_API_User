package com.swd391.bachhoasi_user.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@Schema(description = "Request to manage items in the cart")
public class CartRequest {

    @Schema(description = "ID of the cart product menu", example = "1234")
    private BigDecimal cartProductMenuId;

    @NotNull(message = "Store id should not be null")
    @Schema(description = "ID of the store", example = "5678")
    private BigDecimal storeId;

    @NotNull(message = "Cart id should not be null")
    @Schema(description = "ID of the cart", example = "2345")
    private BigDecimal cartId;

    @NotNull(message = "Product id should not be null")
    @Schema(description = "ID of the menu product (not base product)", example = "3456")
    private BigDecimal productId;

    @NotNull(message = "Quantity should not be null")
    @Max(value = 100000, message = "Quantity should not exceed 100000")
    @Min(value = 1, message = "Quantity should not be less than 1")
    @Schema(description = "Quantity of the product", example = "10")
    private Integer quantity;
}