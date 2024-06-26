package com.swd391.bachhoasi_user.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.swd391.bachhoasi_user.model.constant.PayingMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class OrderRequest {

    @NotNull(message = "Store id should not be null")
    @JsonProperty("store-id")
    private BigDecimal storeId;
    @JsonProperty("cart-id")
    @NotNull(message = "Cart id should not be null")
    private BigDecimal cartId;
    @JsonProperty("payment-method")
    @NotNull(message = "Payment method should not be null")
    private PayingMethod payingMethod;

}
