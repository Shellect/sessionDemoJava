package com.example.sessionDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/register", "/dist/**", "/error", "/errors/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/errors/403") // Страница для 403 ошибки
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")) // Перенаправление на логин
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                );
        return http.build();

    }

}
