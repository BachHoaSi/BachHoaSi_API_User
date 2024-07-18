package com.swd391.bachhoasi_user.service.impl;

import com.swd391.bachhoasi_user.model.dto.response.PaginationResponse;
import com.swd391.bachhoasi_user.model.dto.response.ProductMenuResponse;
import com.swd391.bachhoasi_user.model.dto.response.ProductResponse;
import com.swd391.bachhoasi_user.model.entity.ProductMenu;
import com.swd391.bachhoasi_user.model.entity.Store;
import com.swd391.bachhoasi_user.model.exception.NotFoundException;
import com.swd391.bachhoasi_user.model.exception.ValidationFailedException;
import com.swd391.bachhoasi_user.repository.ProductMenuRepository;
import com.swd391.bachhoasi_user.repository.StoreRepository;
import com.swd391.bachhoasi_user.service.MenuService;
import com.swd391.bachhoasi_user.util.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final ProductMenuRepository productMenuRepository;
    private final AuthUtils authUtils;
    private final StoreRepository storeRepository;

    @Override
    public PaginationResponse<ProductMenuResponse> getMenuItemBelongToStore(Pageable pagination, BigDecimal storeId) {

        var currentUser = authUtils.getUserFromAuthentication();
        if(!currentUser.getId().equals(storeId)){
            throw new ValidationFailedException("You are not authorized to access this store");
        }
        Store store = storeRepository.findById(currentUser.getId()).orElseThrow(() -> new NotFoundException("Store is not found!"));

        Page<ProductMenu> data = productMenuRepository.findMenusByLevelAndType(
                store.getStoreLevel().getId(),
                store.getType().getId(),
                pagination);
        Page<ProductMenuResponse> productMenuResponses = data.map(this::convertToProductMenuResponse);
        return new PaginationResponse<>(productMenuResponses);

    }

    @Override
    public ProductMenuResponse convertToProductMenuResponse(ProductMenu productMenu) {
        return ProductMenuResponse.builder()
                .id(productMenu.getId())
                .product(ProductResponse.builder()
                        .productCode(productMenu.getComposeId().getProduct().getProductCode())
                        .name(productMenu.getComposeId().getProduct().getName())
                        .basePrice(productMenu.getComposeId().getProduct().getBasePrice())
                        .description(productMenu.getComposeId().getProduct().getDescription())
                        .stockQuantity(productMenu.getComposeId().getProduct().getStockQuantity())
                        .urlImage(productMenu.getComposeId().getProduct().getUrlImages())
                        .categoryCode(productMenu.getComposeId().getProduct().getCategory().getCategoryCode())
                        .categoryName(productMenu.getComposeId().getProduct().getCategory().getName())
                        .build())
                .price(productMenu.getPrice())
                .build();
    }



}