package com.swd391.bachhoasi_user.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CartRequest {

    @JsonProperty("item-id")
    private BigDecimal cartProductMenuId;

    @NotNull(message = "Store id should not be null")
    @JsonProperty("store-id")
    private BigDecimal storeId;

    @NotNull(message = "Cart id should not be null")
    @JsonProperty("cart-id")
    private BigDecimal cartId;

    @NotNull(message = "product id should not be null")
    @JsonProperty("product-id")
    private BigDecimal productId;

    @NotNull(message = "Quantity should not be null")
    @Max(value = 100000, message = "Quantity should not exceed 100")
    @Min(value = 1, message = "Quantity should not be less than 1")
    private Integer quantity;

}