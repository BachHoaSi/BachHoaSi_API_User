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

    @JsonProperty("product-code")
    private String productCode;
    private String name;
    @JsonProperty("base-price")
    private BigDecimal basePrice;
    private String description;
    @JsonProperty("stock-quantity")
    private Integer stockQuantity;
    @JsonProperty("url-image")
    private String urlImage;
    @JsonProperty("category-code")
    private String categoryCode;
    @JsonProperty("category-name")
    private String categoryName;

}
