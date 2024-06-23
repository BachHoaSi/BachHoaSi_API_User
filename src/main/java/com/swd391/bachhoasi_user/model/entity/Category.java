package com.swd391.bachhoasi_user.model.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private BigDecimal id;
    @Column(name = "Name", columnDefinition = "varchar", length = 20)
    private String name;
    @Column(name = "CategoryCode")
    private String categoryCode;
    @Column(name = "Description", columnDefinition = "text")
    private String description;
    @Column(name = "CreatedDate")
    private Date createdDate;
    @ManyToOne(targetEntity = Admin.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "CreatedBy")
    private Admin createdBy;
    @Column(name = "UpdatedDate", nullable = true)
    private Date updatedDate;
    @ManyToOne(targetEntity = Admin.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "UpdatedBy", nullable = true)
    private Admin updatedBy;
    @Column(name = "Status", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status;
    @OneToMany(targetEntity = Product.class, fetch = FetchType.LAZY)
    private List<Product> products;
}
