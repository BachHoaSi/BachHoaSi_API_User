package com.swd391.bachhoasi_user.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class FeedbackRequest {

    @JsonProperty("store-id")
    @NotNull(message = "Store id should not be null")
    private BigDecimal storeId;

    @JsonProperty("order-id")
    @NotNull(message = "Order id should not be null")
    private BigDecimal orderId;
    @JsonProperty("order-feedback")
    private String orderFeedback;
    @JsonProperty("delivery-feedback")
    private String deliveryFeedback;


}
