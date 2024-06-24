package com.swd391.bachhoasi_user.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Builder
@Data
@AllArgsConstructor
public class CartDeleteRequest {

    @JsonProperty("item-id")
    private BigDecimal itemId;
    @JsonProperty("cart-id")
    @NotNull(message = "Cart id should not be null")
    private BigDecimal cartId;
    @JsonProperty("store-id")
    @NotNull(message = "Store id should not be null")
    private BigDecimal storeId;




}
