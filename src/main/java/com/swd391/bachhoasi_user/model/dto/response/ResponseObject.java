package com.swd391.bachhoasi_user.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import lombok.Builder;

@Builder
@Schema(description = "Response Object")
public record ResponseObject(
        @Schema(description = "Data object that can be of different types", oneOf = {
                CartProductMenuResponse.class,
                CartResponse.class,
                LoginResponse.class,
                OrderProductMenuResponse.class,
                OrderResponse.class,
                ProductMenuResponse.class,
                ProductResponse.class,
                StoreDetailsResponse.class,
                PaginationResponse.class
        })
    Object data,
    String code,
    Boolean isSuccess,
    HttpStatus status,
    String message
) {
    
}