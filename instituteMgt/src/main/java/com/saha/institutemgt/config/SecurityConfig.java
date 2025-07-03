//package com.saha.institutemgt.config;//package com.saha.institutemgt.config;
////
////import com.saha.institutemgt.user.repository.UserRepository;
//////import com.saha.institutemgt.user.security.jwt.JWTUtil;
//////import com.saha.institutemgt.user.security.jwt.JwtReactiveAuthenticationManager;
//////import com.saha.institutemgt.user.security.jwt.JwtSecurityContextRepository;
////import lombok.RequiredArgsConstructor;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.http.HttpMethod;
////import org.springframework.security.authentication.ReactiveAuthenticationManager;
////import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
////import org.springframework.security.config.web.server.ServerHttpSecurity;
////import org.springframework.security.web.server.SecurityWebFilterChain;
////
////@Configuration
////@EnableReactiveMethodSecurity
////@RequiredArgsConstructor
////public class SecurityConfig {
////
////    private final JWTUtil jwtUtil;
////    private final UserRepository userRepository;
////
////    @Bean
////    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
////        return http
////                .csrf(ServerHttpSecurity.CsrfSpec::disable)
////                .authorizeExchange(exchanges -> exchanges
////                        .pathMatchers(HttpMethod.POST, "/auth/**").permitAll()
////                        .pathMatchers("/public/**").permitAll()
////                        .anyExchange().authenticated()
////                )
////                .authenticationManager(reactiveAuthenticationManager())
////                .securityContextRepository(new JwtSecurityContextRepository(jwtUtil, userRepository))
////                .build();
////    }
////
////    @Bean
////    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
////        return new JwtReactiveAuthenticationManager(jwtUtil, userRepository);
////    }
////}
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers("/stream/**", "/public/**").permitAll() // ✅ Public paths
//                        .anyExchange().authenticated()
//                )
//                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable) // disable if you don't want basic auth
//                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
//                .build();
//    }
//}
//
