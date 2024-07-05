package com.swd391.bachhoasi_user.service.impl;

import com.swd391.bachhoasi_user.model.constant.StoreStatus;
import com.swd391.bachhoasi_user.model.constant.TokenType;
import com.swd391.bachhoasi_user.model.dto.request.LoginDto;
import com.swd391.bachhoasi_user.model.dto.request.StoreCreateRequest;
import com.swd391.bachhoasi_user.model.entity.Store;
import com.swd391.bachhoasi_user.model.entity.StoreLevel;
import com.swd391.bachhoasi_user.model.exception.ActionFailedException;
import com.swd391.bachhoasi_user.model.exception.AuthFailedException;
import com.swd391.bachhoasi_user.repository.StoreLevelRepository;
import com.swd391.bachhoasi_user.security.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swd391.bachhoasi_user.model.dto.response.LoginResponse;
import com.swd391.bachhoasi_user.repository.StoreRepository;
import com.swd391.bachhoasi_user.service.OauthService;


import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{
    private final StoreRepository storeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final StoreLevelRepository storeLevelRepository;
    @Override
    public LoginResponse oauthLogin(LoginDto loginDto) {

        try{



            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getZaloId(), loginDto.getHashPhone()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String accessToken = jwtProvider.generateToken(authentication, TokenType.ACCESS_TOKEN);
            String refreshToken = jwtProvider.generateRefreshToken(authentication);
            try {
                Store store = storeRepository.findByZaloIdAndStatus(SecurityContextHolder.getContext().getAuthentication().getName(), true)
                        .orElseThrow(()->
                                new AuthFailedException("Your account may be waiting for approve or disable during login, please contact admin for more information"));
                return new LoginResponse(accessToken, refreshToken, store.getId());
            }catch (AuthFailedException ex){
                throw new AuthFailedException(ex.getMessage());
            } catch (Exception e){
                throw new AuthFailedException("Your account may have been remove or disable during login");
            }
        }catch (AuthenticationException ex){
            throw new UsernameNotFoundException("The account is not in system, Sign up new store!!!");
        }


    }

    @Override
    public boolean oauthSignup(StoreCreateRequest createRequest) {

        if(storeRepository.existsByPhoneNumber(createRequest.getPhoneNumber())){
            throw new ActionFailedException("Store phone is already exist");
        }
        StoreLevel storeLevel = storeLevelRepository.findByLevel(1).orElseThrow(()->
                new ActionFailedException("Cannot assign store level to store, please contact admin for more information"));
        Store store = Store.builder()
                .name(createRequest.getName())
                .location(createRequest.getLocation())
                .phoneNumber(createRequest.getPhoneNumber())
                .zaloId(createRequest.getZaloId())
                .creationStatus(StoreStatus.PENDING)
                .createdDate(new Date(System.currentTimeMillis()))
                .point(0)
                .status(false)
                .storeLevel(storeLevel)
                .build();
        try {
            storeRepository.save(store);
            return true;
        }catch (Exception e) {
            throw new ActionFailedException("Cannot create store, please try again later");
        }

    }


}