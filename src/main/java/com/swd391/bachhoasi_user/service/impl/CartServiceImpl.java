package com.swd391.bachhoasi_user.service.impl;

import com.swd391.bachhoasi_user.model.constant.CartStatus;
import com.swd391.bachhoasi_user.model.dto.request.CartRequest;
import com.swd391.bachhoasi_user.model.dto.response.CartProductMenuResponse;
import com.swd391.bachhoasi_user.model.dto.response.CartResponse;
import com.swd391.bachhoasi_user.model.dto.response.PaginationResponse;
import com.swd391.bachhoasi_user.model.dto.response.ProductMenuResponse;
import com.swd391.bachhoasi_user.model.entity.Cart;
import com.swd391.bachhoasi_user.model.entity.CartProductMenu;
import com.swd391.bachhoasi_user.model.entity.ProductMenu;
import com.swd391.bachhoasi_user.model.entity.Store;
import com.swd391.bachhoasi_user.model.exception.ActionFailedException;
import com.swd391.bachhoasi_user.model.exception.NotFoundException;
import com.swd391.bachhoasi_user.model.exception.ValidationFailedException;
import com.swd391.bachhoasi_user.repository.CartProductMenuRepository;
import com.swd391.bachhoasi_user.repository.CartRepository;
import com.swd391.bachhoasi_user.repository.ProductMenuRepository;
import com.swd391.bachhoasi_user.repository.StoreRepository;
import com.swd391.bachhoasi_user.service.CartService;
import com.swd391.bachhoasi_user.service.MenuService;
import com.swd391.bachhoasi_user.util.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final StoreRepository storeRepository;
    private final MenuService menuService;
    private final ProductMenuRepository productMenuRepository;
private final AuthUtils authUtils;
    private final CartProductMenuRepository cartProductMenuRepository;



    @Override
    public CartProductMenuResponse addToCart(CartRequest cartRequest) {

        Store store = storeRepository.findById(cartRequest.getStoreId()).orElseThrow(()-> new NotFoundException("Store not found"));

        Cart cart = cartRepository.findByIdAndStoreIdAndCartStatus(cartRequest.getCartId(),cartRequest.getStoreId(),CartStatus.ACTIVE).orElseThrow(()-> new NotFoundException("Cart not found or not active"));
        ProductMenu productMenu = productMenuRepository.findBySubId(cartRequest.getProductId()).orElseThrow(()-> new NotFoundException("Product not found"));
        if(!store.getType().getId().equals(productMenu.getComposeId().getMenu().getStoreType().getId()) || !store.getStoreLevel().getId().equals(productMenu.getComposeId().getMenu().getStoreLevel().getId())){
            throw new ValidationFailedException("The product is not available in this store");
        }

        CartProductMenu oldProductInCart = cartProductMenuRepository.findByCartIdAndProductId(cart.getId(), productMenu.getId());
        if(oldProductInCart != null){
            oldProductInCart.setQuantity(oldProductInCart.getQuantity() + cartRequest.getQuantity());
            oldProductInCart = cartProductMenuRepository.save(oldProductInCart);
            return convertCartProductMenuToProductMenuResponse(oldProductInCart);
        }
        CartProductMenu cartProductMenu = CartProductMenu.builder()
                .cart(cart)
                .product(productMenu)
                .quantity(cartRequest.getQuantity())
                .status(true)
                .build();
        cartProductMenu = cartProductMenuRepository.save(cartProductMenu);
        return convertCartProductMenuToProductMenuResponse(cartProductMenu);
    }

    @Override
    public CartProductMenuResponse updateCartItem(CartRequest cartRequest) {

        try {
            if (cartRequest.getQuantity() == 0) {
                cartProductMenuRepository.deleteById(cartRequest.getCartProductMenuId());
            }else{
                CartProductMenu cartProductMenu = cartProductMenuRepository.findById(cartRequest.getCartProductMenuId()).orElseThrow(()-> new NotFoundException("Cart product menu not found"));
                cartProductMenu.setQuantity(cartRequest.getQuantity());
                cartProductMenu = cartProductMenuRepository.save(cartProductMenu);
                return convertCartProductMenuToProductMenuResponse(cartProductMenu);
            }
            return null;
        }catch (Exception e){
            throw new ActionFailedException("Cannot update cart item!");
        }
    }

    @Override
    public boolean deleteCartItem(BigDecimal itemId, BigDecimal cartId, BigDecimal storeId) {
        if(itemId==null){
            throw new ValidationFailedException("Item id should not be null");
        }
        CartProductMenu deletedItem = cartProductMenuRepository.findByIdAndCartIdAndCartStoreId(itemId, cartId, storeId).orElseThrow(()-> new NotFoundException("Item not found"));
        try {
            cartProductMenuRepository.delete(deletedItem);
            return true;
        }catch (Exception e){
            throw new ActionFailedException("Cannot delete cart item!");
        }
    }

    @Override
    public boolean deleteCart(BigDecimal cartId, BigDecimal storeId) {

        Cart cart = cartRepository.findByIdAndStoreIdAndCartStatus(cartId, storeId, CartStatus.ACTIVE).orElseThrow(()-> new NotFoundException("Cart not found"));
        try{
            cart.setCartStatus(CartStatus.ABANDONED);
            cartRepository.save(cart);
            return true;
        }catch (Exception e){
            throw new ActionFailedException("Cannot delete cart item!");
        }
    }



    @Override
    public CartResponse getCart(BigDecimal storeId) {

        var currentUser = authUtils.getUserFromAuthentication();
        if(!currentUser.getId().equals(storeId)){
            throw new ValidationFailedException("You are not authorized to access this store");
        }
        Store store = storeRepository.findById(currentUser.getId()).orElseThrow(() -> new NotFoundException("Store is not found!"));
        Cart cart = cartRepository.findByStoreIdAndCartStatus(store.getId(), CartStatus.ACTIVE);

        if(cart == null) {
            Cart newCart = new Cart();
            newCart.setStore(store);
            newCart.setCreatedDate(new Date(System.currentTimeMillis()));
            newCart.setCartStatus(CartStatus.ACTIVE);
            newCart.setUpdatedDate(new Date(System.currentTimeMillis()));
            cartRepository.save(newCart);
            return convertCartToCartResponse(newCart);
        }
        return convertCartToCartResponse(cart);
    }

    @Override
    public PaginationResponse<CartProductMenuResponse> getCartItem(BigDecimal cartId, Pageable pageable) {
        Page<CartProductMenu> products = cartProductMenuRepository.findByCartIdAndCartCartStatus(cartId,CartStatus.ACTIVE, pageable);
        Page<CartProductMenuResponse> productMenuResponses = products.map(this::convertCartProductMenuToProductMenuResponse);
        return new PaginationResponse<>(productMenuResponses);
    }

    private CartProductMenuResponse convertCartProductMenuToProductMenuResponse(CartProductMenu cartProductMenu) {

        ProductMenuResponse productMenuResponse = menuService.convertToProductMenuResponse(cartProductMenu.getProduct());

        CartProductMenuResponse cartProductMenuResponse = new CartProductMenuResponse();
        cartProductMenuResponse.setId(cartProductMenu.getId());
        cartProductMenuResponse.setProduct(productMenuResponse);
        cartProductMenuResponse.setQuantity(cartProductMenu.getQuantity());

        return cartProductMenuResponse;

    }


    private CartResponse convertCartToCartResponse(Cart cart) {
        return CartResponse.builder()
                .cartId(cart.getId())
                .createdDate(cart.getCreatedDate())
                .updatedDate(cart.getUpdatedDate())
                .build();
    }
}