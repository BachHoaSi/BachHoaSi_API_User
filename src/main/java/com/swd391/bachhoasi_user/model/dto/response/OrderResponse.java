package com.swd391.bachhoasi_user.model.dto.response;

import com.swd391.bachhoasi_user.model.constant.OrderStatus;
import com.swd391.bachhoasi_user.model.constant.PayingMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
public class OrderResponse {

    private BigDecimal orderId;
    private BigDecimal totalPrice;
    private PayingMethod payingMethod;
    private OrderStatus orderStatus;
    private Integer point;
    private String storeName;
    private String storeAddress;
    private String orderFeedback;
    private String deliveryFeedback;
    private Date createdDate;

}