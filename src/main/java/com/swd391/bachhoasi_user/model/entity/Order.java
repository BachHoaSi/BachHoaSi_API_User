package com.swd391.bachhoasi_user.model.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import com.swd391.bachhoasi_user.model.constant.OrderStatus;
import com.swd391.bachhoasi_user.model.constant.PayingMethod;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id", columnDefinition = "BIGINT")
    private BigDecimal id;
    @Column(name = "SubTotal")
    private BigDecimal subTotal;
    @Column(name = "Discount")
    private Double discount;
    @Column(name = "GrandTotal")
    private BigDecimal grandTotal;
    @Column(name = "OrderDate")
    private LocalDateTime orderDate;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "OrderStatus")
    private OrderStatus orderStatus;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "PayingMethod")
    private PayingMethod payingMethod;
    @Column(name = "Point")
    private Integer point;
    @Column(name = "OrderFeedback", columnDefinition = "text")
    private String orderFeedback;
    @Column(name = "DeliveryFeedback", columnDefinition = "text")
    private String deliveryFeedback;
    @ManyToOne(targetEntity = Admin.class, optional = true)
    @JoinColumn(name = "AdminId")
    private Admin admin;
    @OneToOne(targetEntity = OrderContact.class, optional = false, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private OrderContact orderContact;
    @OneToMany(targetEntity = OrderProductMenu.class,mappedBy = "order" , cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<OrderProductMenu> orderProducts;
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "CreatedDate", nullable = false)
    private Date createdDate;
    @UpdateTimestamp(source = SourceType.DB)
    @Column(name = "UpdatedDate", nullable = true)
    private Date updatedDate;
    @ManyToOne(targetEntity = Shipper.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ShipperId",referencedColumnName = "id", columnDefinition = "bigint")
    private Shipper shipper;
    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "StoreId")
    private Store store;
}