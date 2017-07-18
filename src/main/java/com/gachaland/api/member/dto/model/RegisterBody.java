package com.gachaland.api.member.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gachaland.api.common.Enumerations;
import lombok.Data;

/**
 * Created by chris.j3 on 2017. 6. 21..
 */
@Data
public class RegisterBody {

    @JsonProperty("isDevicePhoneNum")
    private boolean deviceNumber;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("os")
    private Enumerations.OsType osType;      // android,ios

    @JsonProperty("udid")
    private String adid;

//    // TODO
//    @JsonProperty("token")
//    private String token;

}
