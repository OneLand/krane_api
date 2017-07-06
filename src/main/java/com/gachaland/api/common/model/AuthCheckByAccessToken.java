package com.gachaland.api.common.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 카카오 사용자별 토큰(access-token)에 의한 인증을 수행하는 경우 사용
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheckByAccessToken {
}
