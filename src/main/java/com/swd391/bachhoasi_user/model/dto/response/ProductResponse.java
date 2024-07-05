package com.swd391.bachhoasi_user.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {


    private String productCode;
    private String name;
    private BigDecimal basePrice;
    private String description;

    private Integer stockQuantity;

    private String urlImage;

    private String categoryCode;

    private String categoryName;

}