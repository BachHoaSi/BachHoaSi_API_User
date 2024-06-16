package com.swd391.bachhoasi_user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swd391.bachhoasi_user.model.entity.Category;

import java.math.BigDecimal;

public interface CategoryRepository extends JpaRepository<Category,BigDecimal> {
}
