package com.gachaland.api.common.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by jhpark1220 on 2017. 6. 22..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StandardHeader {

    @JsonProperty("status")
    private int code;

    @JsonProperty("message")
    private String message;

}
