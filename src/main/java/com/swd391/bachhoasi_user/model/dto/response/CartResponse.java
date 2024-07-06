package com.swd391.bachhoasi_user.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response representing a cart with its details")
public class CartResponse {

    @Schema(description = "ID of the cart", example = "1234.56")
    private BigDecimal cartId;

    @Schema(description = "Date when the cart was created", example = "2023-07-01")
    private Date createdDate;

    @Schema(description = "Date when the cart was last updated", example = "2023-07-06")
    private Date updatedDate;
}
