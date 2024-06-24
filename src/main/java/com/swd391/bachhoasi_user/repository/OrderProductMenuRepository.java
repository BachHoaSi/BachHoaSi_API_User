package com.swd391.bachhoasi_user.repository;

import com.swd391.bachhoasi_user.model.constant.CartStatus;
import com.swd391.bachhoasi_user.model.entity.CartProductMenu;
import com.swd391.bachhoasi_user.model.entity.OrderProductMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface OrderProductMenuRepository extends BaseBachHoaSiRepository<OrderProductMenu, BigDecimal>{

    Page<OrderProductMenu> findByOrderId(BigDecimal cartId, Pageable pageable);


}
