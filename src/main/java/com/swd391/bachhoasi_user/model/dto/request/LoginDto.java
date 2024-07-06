package com.swd391.bachhoasi_user.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request to log in using Zalo credentials")
public class LoginDto {
    
    @NotBlank(message = "Token should not be blank")
    @Schema(description = "Zalo token", required = true, example = "abc12345")
    private String zaloId;
    
    @NotBlank(message = "HashPassword should not be blank")
    @Schema(description = "Hashed password", required = true, example = "hashedpassword123")
    private String hashPhone;
}