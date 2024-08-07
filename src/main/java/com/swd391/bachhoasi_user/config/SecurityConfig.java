package com.swd391.bachhoasi_user.config;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.swd391.bachhoasi_user.security.BachHoaSiAuthenticationEntryPoint;
import com.swd391.bachhoasi_user.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        @Qualifier("bachHoaSiAuthenticationEntryPoint")
        private final BachHoaSiAuthenticationEntryPoint bachHoaSiAuthenticationEntryPoint;
        private final CorsConfig corsConfig;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http.csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers("/auth/**").permitAll()
                                                .requestMatchers("/auth/zalo/login").permitAll()
                                                .requestMatchers("/auth/zalo/signup").permitAll()
                                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                                .requestMatchers("/type/**").permitAll()
                                                .anyRequest()
                                               .authenticated())
                                .sessionManagement(
                                                sessionManagement -> sessionManagement
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()));
                http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .logout(logout -> {
                                        logout.logoutUrl("/auth/logout");
                                        logout.logoutSuccessHandler(
                                                        (request, response, authorization) -> SecurityContextHolder
                                                                        .clearContext());
                                });
                return http.build();
        }

}