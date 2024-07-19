package com.swd391.bachhoasi_user.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CallBackResponse {
    private int return_code;
    private String return_message;
}