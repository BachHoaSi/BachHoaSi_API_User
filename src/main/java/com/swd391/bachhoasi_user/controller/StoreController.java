package com.swd391.bachhoasi_user.controller;

import com.swd391.bachhoasi_user.model.dto.request.StoreUpdateRequest;
import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;
import com.swd391.bachhoasi_user.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


import static com.swd391.bachhoasi_user.util.BaseUtils.getResponseObjectResponseEntity;


@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {


    private final StoreService storeService;


    @GetMapping("/store-details")
    public ResponseEntity<ResponseObject> getStoreDetails(@RequestParam BigDecimal id) {
        var storeDetails = storeService.getStoreById(id);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .code("GET_STORE_SUCCESS")
                        .data(storeDetails)
                        .isSuccess(true)
                        .status(HttpStatus.OK)
                        .message("Query Success")
                        .build()
        );
    }

    @PutMapping("/update-store")
    public ResponseEntity<ResponseObject> updateStore(@RequestBody @Valid StoreUpdateRequest storeUpdateRequest, BindingResult bindingResult) {
        ResponseEntity<ResponseObject> errors = getResponseObjectResponseEntity(bindingResult);
        if (errors != null) return errors;
        var storeUpdated = storeService.updateStore(storeUpdateRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .code("GET_STORE_SUCCESS")
                        .data(storeUpdated)
                        .isSuccess(true)
                        .status(HttpStatus.OK)
                        .message("Query Success")
                        .build()
        );
    }


}