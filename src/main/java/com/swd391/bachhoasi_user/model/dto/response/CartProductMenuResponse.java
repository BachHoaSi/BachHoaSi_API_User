package com.swd391.bachhoasi_user.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response representing a product in the cart with its details")
public class CartProductMenuResponse {

    @Schema(description = "ID of the cart product menu", example = "1234.56")
    private BigDecimal id;

    @Schema(description = "Details of the product in the cart")
    private ProductMenuResponse product;

    @Schema(description = "Quantity of the product in the cart", example = "10")
    private Integer quantity;
}
