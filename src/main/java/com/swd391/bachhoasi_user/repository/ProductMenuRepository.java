package com.swd391.bachhoasi_user.repository;


import com.swd391.bachhoasi_user.model.entity.ProductMenu;
import com.swd391.bachhoasi_user.model.entity.ProductMenuId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductMenuRepository extends BaseBachHoaSiRepository<ProductMenu, ProductMenuId>{


    @Query("SELECT pm FROM ProductMenu pm " +
            "JOIN pm.composeId.menu m " +
            "JOIN m.storeLevel sl " +
            "JOIN m.storeType st " +
            "JOIN pm.composeId.product p " +
            "WHERE sl.id = :levelId AND st.id = :typeId AND p.status = true AND pm.status = true")
    Page<ProductMenu> findMenusByLevelAndType(@Param("levelId") BigDecimal levelId,
                                              @Param("typeId") BigDecimal typeId,
                                              Pageable pageable);
    @Query("SELECT pm from ProductMenu pm " +
            "JOIN pm.composeId.menu m " +
            "JOIN m.storeLevel sl " +
            "JOIN m.storeType st " +
            "JOIN pm.composeId.product p " +
            "WHERE p.status = true AND pm.status = true AND pm.id = :subId")
    Optional<ProductMenu> findBySubId(BigDecimal subId);
}