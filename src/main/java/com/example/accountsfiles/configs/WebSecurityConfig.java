package com.example.accountsfiles.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.accountsfiles.services.ServiceAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    @Lazy
        private ServiceAccount serviceAccount;
            
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/","/login","/logout","/signup","/deleteAccount").permitAll()
        .anyRequest().authenticated()
    )
    .formLogin(form -> form
        .loginPage("/login")
        .defaultSuccessUrl("/account", true)
        .permitAll()
    )
    .logout(logout -> logout
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .permitAll()
    );
return http.build();
    }


}
