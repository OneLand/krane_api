package com.gachaland.api.common.constants;

public enum ResultCode {
    OK(200),
    UNAUTHORIZED(401),
    NOT_ACCEPTABLE(406),
    INTERNAL_SERVER_ERROR(500),
    NOT_FOUND(404);

    private int code;

    public int getCode() {
        return code;
    }

    ResultCode(int code) {
        this.code = code;
    }

}
