package com.gachaland.api.common.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
