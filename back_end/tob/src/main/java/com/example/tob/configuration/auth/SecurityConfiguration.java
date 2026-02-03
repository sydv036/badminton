package com.example.tob.configuration.auth;

import com.example.tob.configuration.interceptor.Interceptor;
import com.example.tob.configuration.properties.AuthProfiles;
import com.example.tob.services.auth.interfaces.ITokenBlacklistService;
import com.example.tob.services.auth.services.UserDetailServiceCustomize;
import com.example.tob.utils.auth.AuthUtils;
import com.example.tob.utils.auth.CustomizeAuthenticationEntrypoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final Interceptor interceptor;

    private final UserDetailServiceCustomize userDetailServiceCustomize;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, CustomizeAuthenticationEntrypoint customizeAuthenticationEntrypoint) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/auth/**",
                                "/swagger-ui/**",
                                "/api-docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable())
                .oauth2ResourceServer(oauth -> oauth
                        .jwt(Customizer.withDefaults())
                        .authenticationEntryPoint(customizeAuthenticationEntrypoint))

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(interceptor, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailServiceCustomize);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(true);

        ProviderManager providerManager = new ProviderManager(daoAuthenticationProvider);
        return providerManager;
    }

}
