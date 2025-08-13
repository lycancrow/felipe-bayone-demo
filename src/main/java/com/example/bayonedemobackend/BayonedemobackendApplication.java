package com.example.bayonedemobackend;

import com.example.bayonedemobackend.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class BayonedemobackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BayonedemobackendApplication.class, args);
    }


    @EnableWebSecurity
    @Configuration
    static class WebSecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(sm -> sm.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS))
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeHttpRequests(auth -> auth
                            // endpoints permitidos
                            .requestMatchers(HttpMethod.POST, "/BayOne/felipe/newTask").permitAll()
                            .requestMatchers(HttpMethod.GET, "/BayOne/felipe/getTasks").permitAll()
                            .requestMatchers(HttpMethod.GET, "/BayOne/felipe/getTasks/{email}").permitAll()

                            .anyRequest().authenticated()
                    );

            return http.build();
        }

    }
}











