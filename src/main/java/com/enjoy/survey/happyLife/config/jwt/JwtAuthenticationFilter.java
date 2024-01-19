package com.enjoy.survey.happyLife.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.enjoy.survey.happyLife.user.UserDao;
import com.enjoy.survey.happyLife.user.UserEntity;
import com.enjoy.survey.happyLife.config.auth.PrincipalDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // json 형식의 데이터를 파싱할 수 있는 클래스 ObjectMapper
        ObjectMapper om = new ObjectMapper();
        UserEntity loginUser = null;
        try {
            // Unrecognized token 'username': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')
            // 와 같은 에러 발생시에 Json 형식으로 PostMan에서 로그인을 진행하였는지 확인해볼것
            loginUser = om.readValue(request.getInputStream(), UserEntity.class);

            // 로그인할려는 유저가 delete_state 가 true인 경우 비로그인 처리를 하기위해 설정
            UserEntity user = null;
            if(userDao.findByUsername(loginUser.getUsername()) == null) {
                throw new NullPointerException("회원이 존재하지 않습니다.");
            }else {
                user = userDao.findByUsername(loginUser.getUsername());
            }
            boolean delete_state = user.isDelete_state();
            System.out.println("========= fhrm3");
            if (delete_state) throw new NullPointerException("회원이 존재하지 않습니다.");


        } catch (IOException e) {
            e.printStackTrace();
        }

        // 유저네임 패스워드 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                );

        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        return authentication;

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject("cosToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATIONNN_TIME))
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .withClaim("userId", principalDetails.getUser().getId())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
    }
}
