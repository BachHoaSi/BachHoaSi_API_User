package com.swd391.bachhoasi_user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.swd391.bachhoasi_user.model.entity.Product;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductRepository extends BaseBachHoaSiRepository<Product,BigDecimal>{


    Page<Product> findByCategoryIdAndNameContainingIgnoreCase(BigDecimal categoryId, String search, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String search, Pageable pageable);
    Optional<Product> findByProductCode(String code);

}
