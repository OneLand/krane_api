package com.gachaland.api.common.exception;

import com.gachaland.api.common.constants.ResultCode;

public class AuthenticationException extends RuntimeExceptionBase {

    public AuthenticationException(ResultCode errorCode, Throwable throwable, Object... params) {
        super(errorCode, throwable, params);
    }

    public AuthenticationException(ResultCode errorCode, String message, Object... params) {
        super(errorCode, message, params);
    }

    public AuthenticationException(ResultCode errorCode, String message, Throwable throwable, Object... params) {
        super(errorCode, message, throwable, params);
    }
}
