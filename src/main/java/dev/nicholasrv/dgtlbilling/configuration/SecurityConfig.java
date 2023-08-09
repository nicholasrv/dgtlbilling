package dev.nicholasrv.dgtlbilling.configuration;

import dev.nicholasrv.dgtlbilling.handler.CustomAccessDeniedHandler;
import dev.nicholasrv.dgtlbilling.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final BCryptPasswordEncoder encoder;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final UserDetailsService userDetailsService;

    public static final String [] PUBLIC_URLS = {"/user/login/**", "/user/register/**"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().cors().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(STATELESS);
        httpSecurity.authorizeHttpRequests().requestMatchers(PUBLIC_URLS).permitAll();
        httpSecurity.authorizeHttpRequests().requestMatchers(DELETE, "/user/delete/**").hasAnyAuthority("DELETE:USER");
        httpSecurity.authorizeHttpRequests().requestMatchers(DELETE, "/customer/delete/**").hasAnyAuthority("DELETE:CUSTOMER");
        httpSecurity.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).authenticationEntryPoint(customAuthenticationEntryPoint);
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return new ProviderManager(authProvider);
    }
}
