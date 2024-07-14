package com.swd391.bachhoasi_user.controller;

import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;
import com.swd391.bachhoasi_user.service.StoreTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class StoreTypeController {

    private final StoreTypeService storeTypeService;
    @GetMapping
    public ResponseEntity<ResponseObject> getAllStoreTypes() {
        var storeTypes = storeTypeService.getAllStoreTypes();
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .code("GET_STORE_TYPE_SUCCESS")
                        .data(storeTypes)
                        .isSuccess(true)
                        .message("Query Success")
                        .build()
        );
    }

}