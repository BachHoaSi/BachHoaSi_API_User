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
@Schema(description = "Response representing a product menu with its details")
public class ProductMenuResponse {

    @Schema(description = "ID of the product menu", example = "1234.56")
    private BigDecimal id;

    @Schema(description = "Details of the product")
    private ProductResponse product;

    @Schema(description = "Price of the product menu", example = "25.99")
    private BigDecimal price;
}
