package com.enjoy.survey.happyLife.config;


import com.enjoy.survey.happyLife.config.jwt.JwtProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내서버가 응답할때 Json을 자바스크립트에서 처리할 수 있게 할지를 설정하는 코드
//        config.addAllowedOrigin("*"); // 모든 ip 에 응답을 허용하겠다
        // TODO : 해당 CORS 설정을 해주어야 어느 IP 에서든 접근 허용 OriginPattern으로 사용하는 이유는 해당 Credentials(true)
        //  를 설정시에 addAllowedOrigin("*") 설정을 해줄 수 없기 때문이다.
        // TODO : 현재 문제는 리액트 상에서 Headers 부분의 Authorization 값을 받아오지 못하는 문제가 발생하고 있다.
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*"); // 모든 header에 응답을 허용하겠다
        config.addAllowedMethod("*"); // 모든 post, get, put, delete, patch 요청을 허용하겠다.
        config.addExposedHeader(JwtProperties.HEADER_STRING);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
