package com.example.demo2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity
public class Config {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/*").hasRole("USER");
            auth.requestMatchers("/authentication/*").permitAll();
        }).formLogin(formLogin -> formLogin.loginPage("/authentication/login")
                .failureUrl("/authentication/fail")
                .defaultSuccessUrl("/yay")
                .loginProcessingUrl("/authentication/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
        ).requestCache((cache) -> cache
                .requestCache(requestCache)
        );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}

