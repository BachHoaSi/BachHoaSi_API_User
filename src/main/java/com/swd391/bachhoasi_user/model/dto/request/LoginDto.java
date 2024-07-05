package com.swd391.bachhoasi_user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "Token should not blank")
    private String zaloId;
    @NotBlank(message = "HashPhone should not blank")
    private String hashPhone;
}