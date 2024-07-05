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
public class ProductMenuResponse {
    private BigDecimal id;
    private ProductResponse product;
    private BigDecimal price;
}