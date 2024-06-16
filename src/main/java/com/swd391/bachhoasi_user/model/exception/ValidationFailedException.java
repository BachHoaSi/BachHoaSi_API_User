package com.swd391.bachhoasi_user.model.exception;

import org.springframework.http.HttpStatus;

import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;

public class ValidationFailedException extends BachHoaSiBaseException {

    public ValidationFailedException(String message) {
        super(message);
        this.errorResponse = ResponseObject.builder()
        .code("VALIDATION_FAILED")
        .message(message)
        .data(null)
        .isSuccess(false)
        .status(HttpStatus.OK)
        .build();
    }
    
}
