package com.swd391.bachhoasi_user.model.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.swd391.bachhoasi_user.model.constant.StoreStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id")
    private BigDecimal id;
    @Column(name = "Name", columnDefinition = "varchar", length = 64)
    private String name;
    @ManyToOne(targetEntity = StoreType.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "Type")
    private StoreType type;
    @Column(name = "Point")
    private Integer point;
    @Column(name = "ZaloId", nullable = false)
    private String zaloId;
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "CreationStatus")
    private StoreStatus creationStatus;
    @Column(name = "Status", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status;
    @Column(name = "Location", columnDefinition = "text")
    private String location;
    @ManyToOne(targetEntity = StoreLevel.class, optional = false)
    @JoinColumn(name = "StoreLevelId")
    private StoreLevel storeLevel;
    @Column(name = "CreateDate")
    private Date createdDate;
}
