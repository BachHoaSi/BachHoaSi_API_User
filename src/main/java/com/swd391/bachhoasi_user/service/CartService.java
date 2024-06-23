package com.swd391.bachhoasi_user.service;

import com.swd391.bachhoasi_user.model.dto.request.CartRequest;
import com.swd391.bachhoasi_user.model.dto.response.CartResponse;

import java.util.List;

public interface CartService {


    CartResponse addToCart(CartRequest cartRequest);

    CartResponse removeFromCart(CartRequest cartRequest);

    boolean deleteCart(CartRequest cartRequest);

    List<CartResponse> getCart(CartRequest cartRequest);

}
