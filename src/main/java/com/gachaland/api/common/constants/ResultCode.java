package com.gachaland.api.common.constants;

public enum ResultCode {
    OK(200),
    INVALID_INPUT(400),
    UNAUTHORIZED(401),
    NOT_ACCEPTABLE(406),
    INTERNAL_SERVER_ERROR(500),
    NOT_FOUND(404),
    DATA_FAIL_WITH_REMOTE_SERVER(601),      // 원격 서버와 통신하는데 오류가 발생한 경우
    ;

    private int code;

    public int getCode() {
        return code;
    }

    ResultCode(int code) {
        this.code = code;
    }

}
