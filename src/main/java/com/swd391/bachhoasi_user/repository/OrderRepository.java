package com.swd391.bachhoasi_user.repository;

import com.swd391.bachhoasi_user.model.constant.OrderStatus;
import com.swd391.bachhoasi_user.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface OrderRepository extends BaseBachHoaSiRepository<Order, BigDecimal>{


    Page<Order> findByOrderContactPhoneNumberAndOrderStatus(String phoneNumber, OrderStatus orderStatus, Pageable pageable);


}
