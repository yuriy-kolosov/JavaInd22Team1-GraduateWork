package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/webjars/**",
            "/login",
            "/register",
            "/**"
    };

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return login -> {
            UserEntity userEntity = userRepository.findByLogin(login)
                    .orElseThrow(() -> new UsernameNotFoundException("Пользователь с логином " + login + " не найден"));
            return userEntity;
        };
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf((csrf) -> csrf.disable())
//                .authorizeHttpRequests(
//                        authorization ->
//                                authorization
//                                        .requestMatchers("/swagger-ui/**")
//                                        .permitAll()
//                                        .requestMatchers("/ads/**", "/users/**", "/comments/**")
//                                        .authenticated())
//                .cors((cors) -> cors.disable());
//                .httpBasic(withDefaults());
//        return http.build();
//    }
//
        @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf((csrf) -> csrf.disable())
                .httpBasic(withDefaults())
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
