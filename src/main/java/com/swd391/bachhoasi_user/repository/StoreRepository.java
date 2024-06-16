package com.swd391.bachhoasi_user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.swd391.bachhoasi_user.model.entity.Store;

import java.math.BigDecimal;

public interface StoreRepository extends BaseBachHoaSiRepository<Store, BigDecimal> {

    Page<Store> findByNameContainingIgnoreCase(String search, Pageable pageable);

}
