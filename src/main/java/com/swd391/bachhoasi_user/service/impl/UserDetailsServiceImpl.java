package com.swd391.bachhoasi_user.service.impl;

import com.swd391.bachhoasi_user.model.entity.Store;
import com.swd391.bachhoasi_user.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.swd391.bachhoasi_user.model.entity.Admin;


import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StoreRepository storeRepository;

    @Override
    public UserDetails loadUserByUsername(String zaloId) {
        Store store = storeRepository.findByZaloId(zaloId).orElseThrow(() -> new UsernameNotFoundException("ZaloId did not exist in system, Sign up new store!!!"));
        return User.withUsername(store.getZaloId())
                .password(store.getPhoneNumber())
                .authorities("STORE")
                .build();
    }

    private Collection<GrantedAuthority> rolesToAuthority(Admin user) {
        return Collections.singletonList(
            new SimpleGrantedAuthority(String.format("ROLE_%s", user.getRole().name()))
        );
    }
}