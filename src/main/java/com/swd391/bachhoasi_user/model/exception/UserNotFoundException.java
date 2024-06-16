package com.swd391.bachhoasi_user.model.exception;

import org.springframework.http.HttpStatus;

import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;

public class UserNotFoundException extends BachHoaSiBaseException {

    protected UserNotFoundException(String message) {
        super(message);
        this.errorResponse = ResponseObject.builder()
        .code("USER-NOT-FOUND")
        .data(null)
        .message(message)
        .isSuccess(false)
        .status(HttpStatus.OK)
        .build();
    }
}
