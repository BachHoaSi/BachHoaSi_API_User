package com.swd391.bachhoasi_user.model.exception;

import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;
import org.springframework.http.HttpStatus;

public class AllNullException extends BachHoaSiBaseException{

    public AllNullException(String message) {
        super(message);
        this.errorResponse = ResponseObject.builder()
                .code("ALL-FIELDS-NULL")
                .data(null)
                .message(message)
                .isSuccess(false)
                .status(HttpStatus.OK)
                .build();
    }
}
