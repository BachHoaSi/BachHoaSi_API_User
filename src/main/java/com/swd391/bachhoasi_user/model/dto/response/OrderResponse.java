package com.swd391.bachhoasi_user.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.swd391.bachhoasi_user.model.constant.OrderStatus;
import com.swd391.bachhoasi_user.model.constant.PayingMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
public class OrderResponse {

    @JsonProperty("order-id")
    private BigDecimal orderId;
    @JsonProperty("total-price")
    private BigDecimal totalPrice;
    @JsonProperty("Payment-method")
    private PayingMethod payingMethod;
    @JsonProperty("order-status")
    private OrderStatus orderStatus;
    @JsonProperty("point")
    private Integer point;
    @JsonProperty("store-name")
    private String storeName;
    @JsonProperty("store-address")
    private String storeAddress;
    @JsonProperty("order-feedback")
    private String orderFeedback;
    @JsonProperty("delivery-feedback")
    private String deliveryFeedback;
    @JsonProperty("created-date")
    private Date createdDate;

}
