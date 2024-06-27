package com.swd391.bachhoasi_user.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.swd391.bachhoasi_user.model.constant.CartStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id", columnDefinition = "BIGINT")
    private BigDecimal id;
    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "StoreId")
    private Store store;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "Status")
    private CartStatus cartStatus;
    @Column(name = "CreatedDate")
    private Date createdDate;
    @Column(name = "UpdatedDate")
    private Date updatedDate;
    @OneToMany(targetEntity = CartProductMenu.class,mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartProductMenu> cartProducts;
}
