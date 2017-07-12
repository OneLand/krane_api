package com.gachaland.api.common.exception;

import com.gachaland.api.common.constants.ResultCode;
import com.gachaland.api.common.model.ErrorHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHander {

    @ExceptionHandler(RuntimeExceptionBase.class)
    @ResponseBody
    public ErrorHeader handleError(HttpServletRequest req, HttpServletResponse res, RuntimeExceptionBase runtimeExceptionBase) {
        ErrorHeader errorHeader = new ErrorHeader(runtimeExceptionBase.getErrorCode().getCode(), runtimeExceptionBase.getMessage());
        errorLog(req, runtimeExceptionBase);
        res.setStatus(runtimeExceptionBase.getErrorCode().getCode());

        return errorHeader;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public ErrorHeader handleError4InvalidInput(HttpServletRequest req, HttpServletResponse res, RuntimeException e) {
        ErrorHeader errorHeader = new ErrorHeader(ResultCode.INVALID_INPUT.getCode(), e.getMessage());
        errorLog(req, e);
        return errorHeader;
    }

    /**
     * 이 예외는 가급적 어플리케이션에서 잡아서 처리할수 있도록 하자. (ex, cache, 대체 정보 등..)
     *
     * @param req
     * @param rce
     * @return
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(RestClientException.class)
    @ResponseBody
    public ErrorHeader handleError4RestTeplate(HttpServletRequest req, RestClientException rce) {
        ErrorHeader errorHeader = new ErrorHeader(ResultCode.DATA_FAIL_WITH_REMOTE_SERVER.getCode(), "요청된 데이터를 준비하지 못했습니다.");
        errorLog(req, rce);
        return errorHeader;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorHeader handleError4Others(HttpServletRequest req, HttpServletResponse res, Exception exception) {
        ErrorHeader errorHeader = new ErrorHeader(ResultCode.INTERNAL_SERVER_ERROR.getCode(), exception.getMessage());
        errorLog(req, exception);
        return errorHeader;
    }

    private void errorLog(HttpServletRequest req, RuntimeExceptionBase runtimeExceptionBase) {
        StringBuilder stringBuilder = new StringBuilder("[Request] ");
        stringBuilder.append(req.getRequestURL()).append("?").append(req.getQueryString()).append("\n")
                     .append(runtimeExceptionBase.getMessage()).append("\n")
                     .append(runtimeExceptionBase.getDebugBundle()).append("\n");
//                     .append(ExceptionUtils.get.getStackTrace(runtimeExceptionBase));
        log.warn(stringBuilder.toString());
    }

    private void errorLog(HttpServletRequest req, Exception exception) {
        StringBuilder stringBuilder = new StringBuilder("[Request] ");
        stringBuilder.append(req.getRequestURL()).append("?").append(req.getQueryString()).append("\n")
                .append(exception.getMessage()).append("\n");
//                .append(ExceptionUtils.getStackTrace(exception));
        log.warn(stringBuilder.toString());
    }
}
