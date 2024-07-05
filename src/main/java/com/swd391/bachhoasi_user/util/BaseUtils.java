package com.swd391.bachhoasi_user.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import com.swd391.bachhoasi_user.model.exception.ValidationFailedException;


public class BaseUtils {
    private BaseUtils(){}
    public static String generateFileName(MultipartFile multipartFile) {
        String originFileName = multipartFile.getOriginalFilename();
        if(originFileName == null || originFileName.isEmpty()) {
            throw new ValidationFailedException("Name should not be empty");
        }
        if(multipartFile.isEmpty()) {
            throw new ValidationFailedException("Should not use empty files");
        }
        String finalOriginFileName = originFileName.substring(0,originFileName.lastIndexOf("."));
        return String.format("%s-%s",finalOriginFileName,UUID.randomUUID().toString());
    }

    public static String generateProductCode() {
        // Prefix
        String prefix = "BHS";
        // Generate a sequence of 10 random digits
        StringBuilder stringBuilder = new StringBuilder();
        SecureRandom randomized = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            int randomDigit = randomized.nextInt(10); // Generates a random digit between 0 and 9
            stringBuilder.append(randomDigit);
        }
        return prefix + stringBuilder;
    }
    public static ResponseEntity<ResponseObject> getResponseObjectResponseEntity(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return ResponseEntity.ok().body(
                    ResponseObject.builder()
                            .code("VALIDATION_ERROR")
                            .data(errors)
                            .isSuccess(false)
                            .status(HttpStatus.BAD_REQUEST)
                            .message("Validation Error")
                            .build()
            );
        }
        return null;
    }
}