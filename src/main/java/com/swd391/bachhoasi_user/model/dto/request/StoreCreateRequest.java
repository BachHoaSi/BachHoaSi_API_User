package com.swd391.bachhoasi_user.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StoreCreateRequest {

    @NotNull(message = "Store name should not be null")
    private String name;
    @NotNull(message = "Store location should not be null")
    private String location;
    @NotNull(message = "Store phone number should not be null")
    @JsonProperty("phone-number")
    private String phoneNumber;
    @NotNull(message = "Store zalo id should not be null")
    @JsonProperty("zalo-id")
    private String zaloId;





}