package com.swd391.bachhoasi_user.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id", columnDefinition = "BIGINT")
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
