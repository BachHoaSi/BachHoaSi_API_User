package com.swd391.bachhoasi_user.model.dto.response;

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

    private BigDecimal cartId;

    private Date createdDate;

    private Date updatedDate;
}