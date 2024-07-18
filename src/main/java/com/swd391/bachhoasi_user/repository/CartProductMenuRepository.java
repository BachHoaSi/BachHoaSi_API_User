package com.swd391.bachhoasi_user.repository;

import com.swd391.bachhoasi_user.model.constant.CartStatus;
import com.swd391.bachhoasi_user.model.entity.CartProductMenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CartProductMenuRepository extends BaseBachHoaSiRepository<CartProductMenu, BigDecimal>{

    Page<CartProductMenu> findByCartId(BigDecimal cartId, Pageable pageable);

    Page<CartProductMenu> findByCartIdAndCartCartStatus(BigDecimal cartId, CartStatus cartStatus, Pageable pageable);

    Optional<CartProductMenu> findByIdAndCartIdAndCartStoreId(BigDecimal id, BigDecimal cartId, BigDecimal storeId);

    List<CartProductMenu> findByCartIdAndCartStoreId(BigDecimal cartId, BigDecimal storeId);

    @Query("SELECT cpm FROM CartProductMenu cpm WHERE cpm.cart.id = :cartId AND cpm.product.id = :productId")
    CartProductMenu findByCartIdAndProductId(@Param("cartId") BigDecimal cartId, @Param("productId") BigDecimal productId);

}