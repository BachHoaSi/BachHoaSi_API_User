package com.swd391.bachhoasi_user.controller;

import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    @GetMapping
    public ResponseEntity<ResponseObject> getCart() {
        return null;
    }

    @PostMapping
    public ResponseEntity<ResponseObject> addToCart() {
        return null;
    }

    @DeleteMapping("/remove-product-from-cart")
    public ResponseEntity<ResponseObject> removeFromCart() {
        return null;
    }

    @DeleteMapping("/clear-cart")
    public ResponseEntity<ResponseObject> clearCart() {
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateCart() {
        return null;
    }




}
