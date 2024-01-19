package com.enjoy.survey.happyLife.config.jwt;

public interface JwtProperties {
    // 임시 시크릿코드
    String SECRET = "cos";
    int EXPIRATIONNN_TIME = 60000 * 20;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "accessToken";
}
