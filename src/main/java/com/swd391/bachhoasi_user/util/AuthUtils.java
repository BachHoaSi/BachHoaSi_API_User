package com.swd391.bachhoasi_user.util;

import com.swd391.bachhoasi_user.model.entity.Store;
import com.swd391.bachhoasi_user.repository.StoreRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.swd391.bachhoasi_user.model.dto.response.LoginResponse;
import com.swd391.bachhoasi_user.model.entity.Admin;
import com.swd391.bachhoasi_user.model.exception.AuthFailedException;
import com.swd391.bachhoasi_user.repository.AdminRepository;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
public class AuthUtils {

    private final StoreRepository storeRepository;
    public static HttpHeaders getAuthenticationHeader(LoginResponse loginResponse){
        var header =  new HttpHeaders();
        header.add("Authorization", String.format("%s %s", TextUtils.toCamelCase(loginResponse.toString(), true) , loginResponse.getAccessToken()));
        return header;
    }

    public Store getUserFromAuthentication() {
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null) throw new AuthFailedException("User is not authenticated, please login");
            String username = auth.getName();
            return storeRepository.findByZaloId(username).orElseThrow();
        }catch(Exception ex) {
            throw new AuthFailedException("User is not authenticated, please login");
        }
    }
}