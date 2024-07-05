package com.swd391.bachhoasi_user.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class FeedbackRequest {


    @NotNull(message = "Store id should not be null")
    private BigDecimal storeId;


    @NotNull(message = "Order id should not be null")
    private BigDecimal orderId;

    private String orderFeedback;

    private String deliveryFeedback;


}