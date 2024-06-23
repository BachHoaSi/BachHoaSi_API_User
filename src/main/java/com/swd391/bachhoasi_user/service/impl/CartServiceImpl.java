package com.swd391.bachhoasi_user.service.impl;

import com.swd391.bachhoasi_user.model.dto.request.CartRequest;
import com.swd391.bachhoasi_user.model.dto.response.CartResponse;
import com.swd391.bachhoasi_user.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    @Override
    public CartResponse addToCart(CartRequest cartRequest) {
        return null;
    }

    @Override
    public CartResponse removeFromCart(CartRequest cartRequest) {
        return null;
    }

    @Override
    public boolean deleteCart(CartRequest cartRequest) {
        return false;
    }

    @Override
    public List<CartResponse> getCart(CartRequest cartRequest) {
        return null;
    }
}
