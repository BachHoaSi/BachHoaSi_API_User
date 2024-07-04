package com.swd391.bachhoasi_user.controller;

import com.swd391.bachhoasi_user.model.dto.request.LoginDto;
import com.swd391.bachhoasi_user.model.dto.request.StoreCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;
import com.swd391.bachhoasi_user.service.OauthService;

import java.util.ArrayList;
import java.util.List;

import static com.swd391.bachhoasi_user.util.BaseUtils.getResponseObjectResponseEntity;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final OauthService oauthService;
    @PostMapping("/zalo/login")
    public ResponseEntity<ResponseObject> zaloOAuthCallbackHandler(
        @RequestBody LoginDto login,
        BindingResult bindingResult
    ){
        var response = getResponseObjectResponseEntity(bindingResult);
        if (response != null){ return response;}
        var data = oauthService.oauthLogin(login);
        var responseObject = ResponseObject.builder()
                .code("LOGIN_SUCCESS")
                .message("Login successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }

    @PostMapping("/zalo/signup")
    public ResponseEntity<ResponseObject> zaloOAuthSignupHandler(
        @RequestBody @Valid StoreCreateRequest createRequest,
        BindingResult bindingResult
    ){
        var response = getResponseObjectResponseEntity(bindingResult);
        if (response != null){ return response;}
        var data = oauthService.oauthSignup(createRequest);
        var responseObject = ResponseObject.builder()
                .code("SIGNUP_SUCCESS")
                .message("Sign up successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }

}