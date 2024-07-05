package com.swd391.bachhoasi_user.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class CartDeleteRequest {
    private BigDecimal itemId;
    @NotNull(message = "Cart id should not be null")
    private BigDecimal cartId;
    @NotNull(message = "Store id should not be null")
    private BigDecimal storeId;
}