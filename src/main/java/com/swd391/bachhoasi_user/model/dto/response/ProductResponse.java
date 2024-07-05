package com.swd391.bachhoasi_user.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response representing product details")
public class ProductResponse {

    @Schema(description = "Product code", example = "PROD123")
    private String productCode;

    @Schema(description = "Name of the product", example = "Coffee Mug")
    private String name;

    @Schema(description = "Base price of the product", example = "15.99")
    private BigDecimal basePrice;

    @Schema(description = "Description of the product")
    private String description;

    @Schema(description = "Available quantity of the product in stock", example = "100")
    private Integer stockQuantity;

    @Schema(description = "URL of the product image")
    private String urlImage;

    @Schema(description = "Category code of the product", example = "CAT123")
    private String categoryCode;

    @Schema(description = "Name of the product category", example = "Kitchenware")
    private String categoryName;
}
