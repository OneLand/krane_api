package com.gachaland.api.common.exception;

import com.gachaland.api.common.constants.ResultCode;

public class GameRoomException extends RuntimeExceptionBase {

    public GameRoomException(ResultCode errorCode, Throwable throwable, Object... params) {
        super(errorCode, throwable, params);
    }

    public GameRoomException(ResultCode errorCode, String message, Object... params) {
        super(errorCode, message, params);
    }

    public GameRoomException(ResultCode errorCode, String message, Throwable throwable, Object... params) {
        super(errorCode, message, throwable, params);
    }
}
