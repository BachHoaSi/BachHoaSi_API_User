package com.swd391.bachhoasi_user.controller;

import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/orders")
    public ResponseEntity<ResponseObject> getOrders() {
        return null;
    }

    @GetMapping("/order-details")
    public ResponseEntity<ResponseObject> getOrderDetails() {
        return null;
    }

    @PostMapping
    public ResponseEntity<ResponseObject> addOrder() {
        return null;
    }


}
