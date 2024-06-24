package com.swd391.bachhoasi_user.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductMenuResponse {

    private BigDecimal id;

    private ProductMenuResponse product;

    private Integer quantity;

}
