package com.swd391.bachhoasi_user.repository;

import com.swd391.bachhoasi_user.model.entity.StoreType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StoreTypeRepository extends BaseBachHoaSiRepository<StoreType, BigDecimal>{

    List<StoreType> findByStatus(boolean status);



}