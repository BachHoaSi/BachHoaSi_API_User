package com.swd391.bachhoasi_user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swd391.bachhoasi_user.model.entity.Admin;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, BigDecimal> {

    Optional<Admin> findByUsername(String username);
}
