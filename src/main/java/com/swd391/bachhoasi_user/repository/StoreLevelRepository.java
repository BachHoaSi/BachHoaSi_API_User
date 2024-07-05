package com.swd391.bachhoasi_user.repository;


import com.swd391.bachhoasi_user.model.entity.StoreLevel;

import java.math.BigDecimal;
import java.util.Optional;

public interface StoreLevelRepository extends BaseBachHoaSiRepository<StoreLevel, BigDecimal>{

    Optional<StoreLevel> findByLevel(Integer level);

}