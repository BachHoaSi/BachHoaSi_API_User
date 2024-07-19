package com.swd391.bachhoasi_user.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CallBackRequest {
    private String data;
    private String mac;

}