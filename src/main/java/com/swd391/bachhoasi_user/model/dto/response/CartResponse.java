package com.swd391.bachhoasi_user.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CartResponse {

    @JsonProperty("cart_id")
    private BigDecimal cartId;

    @JsonProperty("created-date")
    private Date createdDate;

    @JsonProperty("updated-date")
    private Date updatedDate;
}