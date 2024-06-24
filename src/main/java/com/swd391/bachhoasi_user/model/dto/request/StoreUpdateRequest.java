package com.swd391.bachhoasi_user.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class StoreUpdateRequest {

    @NotNull(message = "Store id should not be null")
    private BigDecimal id;

    private String name;

    @Pattern(regexp = "(84|0[35789])([0-9]{8})\\b", message = "Invalid phone number")
    @JsonProperty("phone-number")
    private String phoneNumber;

    private String location;

}
