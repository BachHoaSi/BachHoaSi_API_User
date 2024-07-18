package com.swd391.bachhoasi_user.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        try {
            UserDetails user = userDetailsService.loadUserByUsername(username);
            boolean check = passwordEncoder.matches(user.getPassword(),password);
            if (check) {
                return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
            } else {
                throw new AuthenticationException("Invalid information!") {};
            }
        }catch (UsernameNotFoundException e) {
            throw new AuthenticationException("The account is not in system, create new store!") {};
        }


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}