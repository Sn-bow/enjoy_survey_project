package com.enjoy.survey.happyLife.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.enjoy.survey.happyLife.config.jwt.JwtProperties;

public class JWTUsernameCheck {

    public String usernameCheck(String jwtToken) {
        String jwt = jwtToken.replace(JwtProperties.TOKEN_PREFIX, "");

        return JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
                .build().verify(jwt)
                .getClaim("username")
                .asString();
    }
}
