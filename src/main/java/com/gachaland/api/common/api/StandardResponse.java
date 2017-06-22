package com.gachaland.api.common.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by jhpark1220 on 2017. 6. 22..
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StandardResponse extends StandardHeader {
    private final Object body;
    public StandardResponse(int code, String message, Object body) {
        super(code, message);
        this.body = body;
    }

    public StandardResponse(int code, String message) {
        super(code, message);
        this.body = null;
    }
}
