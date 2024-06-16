package com.swd391.bachhoasi_user.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;
import com.swd391.bachhoasi_user.service.OauthService;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final OauthService oauthService;
    @GetMapping("/zalo/callback")
    public ResponseEntity<ResponseObject> zaloOAuthCallbackHandler(
        @RequestParam(name = "code") String code,
        @RequestParam("state") String state,
        @RequestParam("code_challenge") String codeChallenge
    ){
        return null;
    }

}
