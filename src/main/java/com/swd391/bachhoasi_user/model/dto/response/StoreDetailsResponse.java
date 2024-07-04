package com.swd391.bachhoasi_user.model.dto.response;



import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("store-type")
    private String storeType;
    private Integer point;
    @JsonProperty("phone-number")
    private String phoneNumber;
    private String location;
    @JsonProperty("store-level")
    private Integer storeLevel;

}