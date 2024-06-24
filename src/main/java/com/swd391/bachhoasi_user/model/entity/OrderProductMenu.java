package com.swd391.bachhoasi_user.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "OrderProductMenu")
public class OrderProductMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id", columnDefinition = "BIGINT")
    private BigDecimal id;
    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId", referencedColumnName = "id", columnDefinition = "bigint")
    private Order order;
    @Column(name = "Quantity")
    private Integer quantity;
    @OneToOne(targetEntity = ProductMenu.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductMenuId", referencedColumnName = "id",columnDefinition = "bigint", nullable = false)
    private ProductMenu product;
}
