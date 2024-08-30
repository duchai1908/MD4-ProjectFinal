package com.ra.md4projectapi.security.config;

import com.ra.md4projectapi.constants.RoleName;
import com.ra.md4projectapi.security.exception.AccessDenied;
import com.ra.md4projectapi.security.exception.JwtEntryPoint;
import com.ra.md4projectapi.security.jwt.JwtTokenFilter;
import com.ra.md4projectapi.security.principle.UserDetailCustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailCustomService userDetailCustomService;
    private final AccessDenied accessDenied;
    private final JwtEntryPoint jwtEntryPoint;
    private final JwtTokenFilter jwtTokenFilter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(url -> url.requestMatchers("/admin/**").hasAuthority(RoleName.ROLE_ADMIN.name())
                        .requestMatchers("/api/v1/moderator/**").hasAuthority(RoleName.ROLE_MODERATOR.name())
                        .requestMatchers("/user/**").hasAuthority(RoleName.ROLE_USER.name())
                        .anyRequest().permitAll())
                .authenticationProvider(authenticationProvider())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtEntryPoint).accessDeniedHandler(accessDenied))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailCustomService);
        return authProvider;
    }

}
