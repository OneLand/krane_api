package com.gachaland.api.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StandardHeader {

	@ApiModelProperty(required = true, notes = "0인 경우가 성공")
    @JsonProperty("code")
    private int code;

	@ApiModelProperty(required = true)
    @JsonProperty("message")
    private String message;
}
