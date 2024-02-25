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

@Configuration
@EnableWebSecurity
public class Config {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/*").hasRole("USER");
            auth.requestMatchers("/authentication/*").permitAll();
        }).formLogin(formLogin -> formLogin.loginPage("/authentication/login")
                .failureUrl("/authentication/fail")
                .defaultSuccessUrl("/yay") //this link is where it will go after successful login if we were not redirected to login page
                .successForwardUrl("/yay") // this link overrides redirect to page from where redirect to login occured, this endpoint must support POST
                .loginProcessingUrl("/authentication/login") // login form post url endpoint otherwise it is /login
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
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

