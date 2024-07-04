package com.swd391.bachhoasi_user.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "Token should not blank")
    @JsonProperty("zalo-code")
    private String zaloId;
    @NotBlank(message = "HashPhone should not blank")
    @JsonProperty("hash-password")
    private String hashPhone;
}