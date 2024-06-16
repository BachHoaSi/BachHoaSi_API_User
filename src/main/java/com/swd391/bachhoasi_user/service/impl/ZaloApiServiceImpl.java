package com.swd391.bachhoasi_user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.swd391.bachhoasi_user.model.dto.response.ZaloAccessTokenResponse;
import com.swd391.bachhoasi_user.model.dto.response.ZaloUserResponse;
import com.swd391.bachhoasi_user.model.exception.AuthFailedException;
import com.swd391.bachhoasi_user.service.ZaloApiService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ZaloApiServiceImpl implements ZaloApiService {
    @Value("${zalo.client-id}")
    private String clientId;
    @Value("${zalo.client-secret}")
    private String clientSecret;
    @Value("${zalo.code-verify}")
    private String codeVerify;
    @Value("${zalo.end-point.oauth2}")
    private String zaloEndpoint;
    @Value("${zalo.app-id}")
    private String appId;
    @Value("${zalo.end-point.me}")
    private String meEndpoint;

    @Override
    public String getAccessToken(String code) {
        var template = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.set("secret_key", clientSecret);
        Map<String,String> body = new HashMap<>();
        body.put("code", code);
        body.put("app_id", appId);
        body.put("grant_type", "authorization_code");
        body.put("code_verifier", codeVerify);
        HttpEntity<Map<String,String>> requestEntity = new HttpEntity<>(body,header);
        ResponseEntity<ZaloAccessTokenResponse> response = template.exchange(
            getZaloAccessTokenEndpoint(), HttpMethod.POST, requestEntity,ZaloAccessTokenResponse.class);
        if(response.getStatusCode() == HttpStatus.OK) {
            var responseBody = response.getBody();
            if (responseBody == null) throw new AuthFailedException("");
            return responseBody.getAccessToken();
        } else {
            throw new AuthFailedException("");
        }
    }

    @Override
    public ZaloUserResponse getZaloUserInformation(String accessToken) {
        var restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ZaloUserResponse> response = restTemplate.exchange(
            getZaloMeEndpoint(), 
            HttpMethod.GET, 
            requestEntity,
            ZaloUserResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new AuthFailedException("");
        }
    }

    private String getZaloAccessTokenEndpoint() {
        return String.format("%s/v4/access_token", zaloEndpoint);
    }
    private String getZaloMeEndpoint() {
        return String.format("%s/v2.0/me?fields=id,name", meEndpoint);
    }
}
