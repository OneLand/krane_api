package com.gachaland.api.common.exception;

import com.gachaland.api.common.constants.ResultCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

/**
 * 추가로 정의하는 모든 Exception은 이 클래스를 상속받는다.
 */
@ToString
@EqualsAndHashCode(callSuper=true)
public class RuntimeExceptionBase extends RuntimeException {
    @Getter
    private ResultCode errorCode;
    @Getter
    private Object[] params;

    public RuntimeExceptionBase(ResultCode errorCode, Throwable throwable, Object... params) {
        super();
        this.params = params;
    }

    public RuntimeExceptionBase(ResultCode errorCode, String message, Object... params) {
        super(message);
        this.params = params;
        this.errorCode = errorCode == ResultCode.OK ? ResultCode.INTERNAL_SERVER_ERROR : errorCode;
    }

    public RuntimeExceptionBase(ResultCode errorCode, String message, Throwable throwable, Object... params) {
        super(message, throwable);
        this.params = params;
        this.errorCode = errorCode == ResultCode.OK ? ResultCode.INTERNAL_SERVER_ERROR : errorCode;
    }

    /**
     * 상속받는 Exception 에서 필요한 경우 재정의 한다.
     * @return
     */
    public String getDebugBundle() {
        StringBuilder sBuilder = new StringBuilder("[data-params] ");
        sBuilder.append(Arrays.toString(params));

        return sBuilder.toString();
    }
}
