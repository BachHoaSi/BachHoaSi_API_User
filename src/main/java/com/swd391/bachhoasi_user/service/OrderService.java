package com.swd391.bachhoasi_user.service;

import com.swd391.bachhoasi_user.model.dto.request.OrderRequest;
import com.swd391.bachhoasi_user.model.dto.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface OrderService {

    OrderResponse placeOrder(OrderRequest order);

    OrderResponse getOrderDetails(BigDecimal id);

    OrderResponse getAllOrders(BigDecimal userId);



}
