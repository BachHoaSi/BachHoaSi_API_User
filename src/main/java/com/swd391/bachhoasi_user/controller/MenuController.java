package com.swd391.bachhoasi_user.controller;

import com.swd391.bachhoasi_user.model.dto.request.SearchRequestParamsDto;
import com.swd391.bachhoasi_user.model.dto.response.PaginationResponse;
import com.swd391.bachhoasi_user.model.dto.response.ProductMenuResponse;
import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;
import com.swd391.bachhoasi_user.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {


    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<ResponseObject> getMenu(
            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pagination,
            @RequestParam(required = true, name = "store-id") BigDecimal storeId) {

        PaginationResponse<ProductMenuResponse> menu = menuService.getMenuItemBelongToStore(pagination, storeId);
        var responseObject = ResponseObject.builder()
                .code("PRODUCT_GET_SUCCESS")
                .message("Get products successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(menu)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }

}
