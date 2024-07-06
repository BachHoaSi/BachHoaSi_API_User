package com.swd391.bachhoasi_user.model.dto.request;

import com.swd391.bachhoasi_user.model.constant.PayingMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@Schema(description = "Request to place an order")
public class OrderRequest {

    @NotNull(message = "Store id should not be null")
    @Schema(description = "ID of the store", required = true, example = "5678.90")
    private BigDecimal storeId;

    @NotNull(message = "Cart id should not be null")
    @Schema(description = "ID of the cart", required = true, example = "2345.67")
    private BigDecimal cartId;

    @NotNull(message = "Payment method should not be null")
    @Schema(description = "Payment method for the order", required = true)
    private PayingMethod payingMethod;

    @NotNull(message = "Delivery time is required")
    @Schema(description = "Time want to delivery!", required = true)
    private LocalDateTime deliveryTime;

}