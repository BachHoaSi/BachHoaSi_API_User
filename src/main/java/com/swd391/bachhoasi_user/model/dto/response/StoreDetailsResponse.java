package com.swd391.bachhoasi_user.model.dto.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDetailsResponse {

    private String name;
    private String storeType;
    private Integer point;
    private String phoneNumber;
    private String location;
    private Integer storeLevel;

}