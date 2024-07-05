package com.swd391.bachhoasi_user.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoreUpdateRequest {

    @NotNull(message = "Store id should not be null")
    private BigDecimal id;

    private String name;

    @Pattern(regexp = "\\b(84[35789][0-9]{8})\\b|\\b(0[35789][0-9]{8})\\b", message = "Invalid phone number")
    private String phoneNumber;

    private String location;

}