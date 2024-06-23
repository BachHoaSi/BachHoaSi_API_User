package com.swd391.bachhoasi_user.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated
public class StoreUpdateRequest {

    @NotEmpty(message = "Store id should not be null or empty")
    private BigDecimal id;

    //@NotEmpty(message = "Product name should not be null or empty")
    private String name;

    //@NotEmpty(message = "Phone number should not be empty")
    @JsonProperty("phone-number")
    private String phoneNumber;

    //@NotEmpty(message = "Address should not be empty")
    private String location;

}
