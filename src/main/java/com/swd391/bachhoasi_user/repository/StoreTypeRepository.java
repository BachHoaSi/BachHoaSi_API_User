package com.swd391.bachhoasi_user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swd391.bachhoasi_user.model.entity.StoreType;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface StoreTypeRepository extends BaseBachHoaSiRepository<StoreTypeRepository, BigDecimal> {
    Page<StoreType> findByName(String name, Pageable pageable);
    Optional<StoreType> findByName(String name);
    Page<StoreType> findByDescription(String description, Pageable pageable);
    @Query("select s from StoreType s where s.name like %:keyword% or s.description like %:keyword%")
    Page<StoreType> findByNameOrDescription(String keyword, Pageable pageable);
}
