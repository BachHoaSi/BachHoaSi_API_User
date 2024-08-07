package com.swd391.bachhoasi_user.controller;

import com.swd391.bachhoasi_user.model.dto.request.CartDeleteRequest;
import com.swd391.bachhoasi_user.model.dto.request.CartRequest;
import com.swd391.bachhoasi_user.model.dto.response.CartResponse;
import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;
import com.swd391.bachhoasi_user.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    @Operation(summary = "Get cart",description = "Get cart by store id")
    @GetMapping()
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200")
    })
    public ResponseEntity<ResponseObject> getCart(@RequestParam(required = true, name = "storeId") BigDecimal storeId) {



        CartResponse data = cartService.getCart(storeId);
        var responseObject = ResponseObject.builder()
                .code("CART_GET_SUCCESS")
                .message("Get cart successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }
    @Operation(summary = "Get cart item",description = "Get cart item by cart id")
    @GetMapping("/items")
    public ResponseEntity<ResponseObject> getCartItem(
            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pagination,
            @RequestParam(required = true, name = "cartId") BigDecimal cartId) {
        var data = cartService.getCartItem(cartId, pagination);
        var responseObject = ResponseObject.builder()
                .code("CART_GET_SUCCESS")
                .message("Get cart successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }

    @Operation(summary = "Add to cart",description = "Add item to cart, item-id is product-menu-id")
    @PostMapping()
    public ResponseEntity<ResponseObject> addToCart(@RequestBody @Valid CartRequest cartRequest, BindingResult bindingResult) {
        var response = getResponseObjectResponseEntity(bindingResult);
        if (response != null){ return response;}
        var data = cartService.addToCart(cartRequest);
        var responseObject = ResponseObject.builder()
                .code("CART_ADD_SUCCESS")
                .message("Add to cart successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }
    @Operation(summary = "Delete cart item",description = "Delete item from cart, item-id is cart-product-menu-id (id of the cart item, not product id)")
    @DeleteMapping("/item")
    public ResponseEntity<ResponseObject> deleteCartItem(@RequestBody @Valid CartDeleteRequest cartRequest, BindingResult bindingResult) {
        var response = getResponseObjectResponseEntity(bindingResult);
        if (response != null){ return response;}
        var data = cartService.deleteCartItem(cartRequest.getItemId(), cartRequest.getCartId(), cartRequest.getStoreId());
        var responseObject = ResponseObject.builder()
                .code("ITEM_REMOVE_SUCCESS")
                .message("Delete cart item successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }

    @Operation(summary = "Update cart item",description = "Update item in cart, item-id is cart-product-menu-id (id of the cart item, not product id)")
    @PutMapping("/item")
    public ResponseEntity<ResponseObject> updateCartItem(@RequestBody @Valid CartRequest cartRequest,BindingResult bindingResult) {
        var response = getResponseObjectResponseEntity(bindingResult);
        if (response != null){ return response;}
        var data = cartService.updateCartItem(cartRequest);
        var responseObject = ResponseObject.builder()
                .code("CART_UPDATE_SUCCESS")
                .message("Update cart item successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseObject> deleteCart(@RequestBody @Valid CartDeleteRequest cartRequest, BindingResult bindingResult) {
        var response =  getResponseObjectResponseEntity(bindingResult);
        if (response != null){ return response;}
        var data = cartService.deleteCart(cartRequest.getCartId(), cartRequest.getStoreId());
        var responseObject = ResponseObject.builder()
                .code("CART_DELETE_SUCCESS")
                .message("Delete cart successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }

    private ResponseEntity<ResponseObject> getResponseObjectResponseEntity(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return ResponseEntity.ok().body(
                    ResponseObject.builder()
                            .code("VALIDATION_ERROR")
                            .data(errors)
                            .isSuccess(false)
                            .status(HttpStatus.BAD_REQUEST)
                            .message("Validation Error")
                            .build()
            );
        }
        return null;
    }


}