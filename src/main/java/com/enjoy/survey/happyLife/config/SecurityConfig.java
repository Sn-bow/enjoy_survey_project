package com.enjoy.survey.happyLife.config;

import com.enjoy.survey.happyLife.user.UserDao;
import com.enjoy.survey.happyLife.config.jwt.JwtAuthenticationFilter;
import com.enjoy.survey.happyLife.config.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;
    private final UserDetailsService userDetailsService;
    private final UserDao userDao;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);
        sharedObject.userDetailsService(this.userDetailsService);
        AuthenticationManager authenticationManager = sharedObject.build();
        http.authenticationManager(authenticationManager);

        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilter(corsConfig.corsFilter()); // cors 허용 설정
        http.formLogin(login -> login.disable());
        http.httpBasic(basic -> basic.disable());
        http.addFilter(new JwtAuthenticationFilter(authenticationManager));
        http.addFilter(new JwtAuthorizationFilter(authenticationManager, userDao));
        http.authorizeRequests(request ->
                request.requestMatchers("/user/**")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")
                        .anyRequest().permitAll()
        );

        return http.build();
    }
}
