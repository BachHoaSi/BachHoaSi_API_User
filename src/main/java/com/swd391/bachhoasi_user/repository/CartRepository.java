package com.swd391.bachhoasi_user.repository;

import com.swd391.bachhoasi_user.model.constant.CartStatus;
import com.swd391.bachhoasi_user.model.entity.Cart;

import java.math.BigDecimal;
import java.util.Optional;

public interface CartRepository extends BaseBachHoaSiRepository<Cart, BigDecimal>{

    Cart findByStoreIdAndCartStatus(BigDecimal storeId, CartStatus cartStatus);

    Optional<Cart> findByIdAndStoreIdAndCartStatus(BigDecimal cartId,BigDecimal storeId, CartStatus cartStatus);

}
