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
@Schema(description = "Request to feedback")
public class FeedbackRequest {
    @Schema(description = "ID of store Id", example = "1234")
    @NotNull(message = "Store id should not be null")
    private BigDecimal storeId;
    @Schema(description = "ID of the order Id", example = "1234")
    @NotNull(message = "Order id should not be null")
    private BigDecimal orderId;
    @Schema(description = "Order Feedback", example = "1234")
    private String orderFeedback;
    @Schema(description = "Delivery Feedback Feedback", example = "1234")
    private String deliveryFeedback;
}