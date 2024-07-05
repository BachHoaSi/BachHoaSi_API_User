package com.swd391.bachhoasi_user.repository;

import com.swd391.bachhoasi_user.model.constant.StoreStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.swd391.bachhoasi_user.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, BigDecimal> {

    Page<Store> findByNameContainingIgnoreCase(String search, Pageable pageable);

    Optional<Store> findByZaloId(String zaloId);

    Optional<Store> findByZaloIdAndStatus(String zaloId, boolean status);

    Optional<Store> findById(BigDecimal id);

    Optional<Store> findByName(String name);

    Optional<Store> findByPhoneNumber(String phoneNumber);

    Boolean existsByPhoneNumber(String phoneNumber);

}