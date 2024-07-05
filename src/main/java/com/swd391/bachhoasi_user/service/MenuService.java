package com.swd391.bachhoasi_user.service;

import com.swd391.bachhoasi_user.model.dto.response.PaginationResponse;
import com.swd391.bachhoasi_user.model.dto.response.ProductMenuResponse;
import com.swd391.bachhoasi_user.model.entity.ProductMenu;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface MenuService {

    PaginationResponse<ProductMenuResponse> getMenuItemBelongToStore(Pageable pagination, BigDecimal storeId);

    ProductMenuResponse convertToProductMenuResponse(ProductMenu productMenu);
}