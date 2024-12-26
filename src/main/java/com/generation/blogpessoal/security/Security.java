package com.generation.blogpessoal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/postagens", "/postagens/{id}", "/postagens/titulo/**").permitAll()  // Permite acesso sem autenticação para /postagens, /postagens/{id}, e /postagens/titulo/{titulo}
                .requestMatchers("/postagens").permitAll()  // Permite o acesso sem autenticação para a criação de postagens (POST)
                .requestMatchers(HttpMethod.PUT, "/postagens").permitAll()  // Permite acesso sem autenticação para PUT
                .requestMatchers(HttpMethod.DELETE, "/postagens/{id}").permitAll()  // Permite acesso sem autenticação para DELETE
                .anyRequest().authenticated()  // Exige autenticação para outras requisições
            )
            .csrf(csrf -> csrf.disable())  // Desabilita CSRF (para facilitar os testes com ferramentas como o Postman)
            .httpBasic(); // Habilita autenticação básica sem a necessidade do método deprecated
        return http.build();
    }
}
