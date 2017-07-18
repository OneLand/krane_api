package com.gachaland.api.common.interceptor;

import com.gachaland.api.common.constants.Constants;
import com.gachaland.api.common.constants.ResultCode;
import com.gachaland.api.common.exception.AuthenticationException;
import com.gachaland.api.common.exception.RuntimeExceptionBase;
import com.gachaland.api.common.model.AuthCheckByAccessToken;
import com.gachaland.api.common.model.UserSession;
import com.gachaland.api.member.service.MemberTokenService;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jhpark1220 on 2017. 7. 6..
 */
@Slf4j
@Component
public class AuthAccessTokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MemberTokenService memberTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // for test
        String debugId = request.getHeader(Constants.REQ__DEBUG_USER_ID_PARAM);
        if (debugId != null) {
            UserSession userSession = memberTokenService.checkMeDebug(debugId);
            request.setAttribute(Constants.REQ_ATTR_USER, userSession);
            MDC.put(Constants.MDC_REQUEST_ID, userSession);
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AuthCheckByAccessToken authCheck = handlerMethod.getMethodAnnotation(AuthCheckByAccessToken.class);
        if (authCheck != null) {
            String accessToken = request.getHeader(Constants.REQ_ATTR_USER_TOKEN);
            if (StringUtils.isEmpty(accessToken))
                throw new AuthenticationException(ResultCode.UNAUTHORIZED, "사용자 토큰이 없습니다.");

            String memberId = request.getHeader(Constants.REQ_ATTR_USER_ID);
            if (StringUtils.isEmpty(memberId))
                throw new AuthenticationException(ResultCode.UNAUTHORIZED, "사용자 토큰이 없습니다.");

            UserSession userSession = memberTokenService.checkMe(accessToken, memberId);
            if (userSession == null || userSession.getMember() == null || userSession.getToken() == null) {
                throw new AuthenticationException(ResultCode.UNAUTHORIZED, "사용자 토큰이 없습니다.");
            }

            request.setAttribute(Constants.REQ_ATTR_USER, userSession);
            MDC.put(Constants.MDC_REQUEST_ID, userSession);
        }
        return true;
    }
}
