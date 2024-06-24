package com.swd391.bachhoasi_user.service;

import com.swd391.bachhoasi_user.model.dto.request.CartRequest;
import com.swd391.bachhoasi_user.model.dto.response.CartProductMenuResponse;
import com.swd391.bachhoasi_user.model.dto.response.CartResponse;
import com.swd391.bachhoasi_user.model.dto.response.PaginationResponse;
import com.swd391.bachhoasi_user.model.entity.Cart;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public interface CartService {


    CartProductMenuResponse addToCart(CartRequest cartRequest);

    CartProductMenuResponse updateCartItem(CartRequest cartRequest);

    boolean deleteCartItem(BigDecimal itemId, BigDecimal cartId, BigDecimal storeId);

    boolean deleteCart(BigDecimal cartId, BigDecimal storeId);

    CartResponse getCart(BigDecimal storeId);

    PaginationResponse<CartProductMenuResponse> getCartItem(BigDecimal cartId, Pageable pageable);
}
