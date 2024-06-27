package com.swd391.bachhoasi_user.service;

import com.swd391.bachhoasi_user.model.constant.OrderStatus;
import com.swd391.bachhoasi_user.model.dto.request.FeedbackRequest;
import com.swd391.bachhoasi_user.model.dto.request.OrderRequest;
import com.swd391.bachhoasi_user.model.dto.response.OrderProductMenuResponse;
import com.swd391.bachhoasi_user.model.dto.response.OrderResponse;
import com.swd391.bachhoasi_user.model.dto.response.PaginationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface OrderService {

    OrderResponse placeOrder(OrderRequest order);

    PaginationResponse<OrderProductMenuResponse> getOrderDetails(BigDecimal id, Pageable pageable);

    PaginationResponse<OrderResponse> getAllOrders(BigDecimal storeId, OrderStatus orderStatus, Pageable pageable);
    OrderResponse addFeedback(FeedbackRequest feedbackRequest);
}
