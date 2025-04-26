package com.Demo.CloudVendorRESTAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails userDetailsone= User.withUsername("user1")
                .password(passwordEncoder().encode("pass1"))
                .roles("USER").build();
        UserDetails userDetailstwo= User.withUsername("user2").password(passwordEncoder().encode("pass2"))
                .roles("USER").build();
        UserDetails admin= User.withUsername("admin").password(passwordEncoder().encode("admin"))
                .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(userDetailsone, userDetailstwo, admin);

    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/cloudvendor/welcome").permitAll() // Allow all requests to /cloudvendor
                        .anyRequest().authenticated() // Require authentication for other requests
                )
                .httpBasic(Customizer.withDefaults())
                .logout(logout -> logout
                .logoutUrl("/logout") // URL to trigger logout
                .logoutSuccessUrl("/cloudvendor/welcome") // Redirect after successful logout
                .invalidateHttpSession(true) // Invalidate session
                .deleteCookies("JSESSIONID")
                                .addLogoutHandler(customLogoutHandler())

                );
        http.headers(headers->headers.cacheControl(cache->
                cache.disable()));
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)
                .expiredUrl("/cloudvendor/welcome")
        );

//        http.sessionManagement(session->
//                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));// Enable Basic Authentication for /cloudvendor;
        return http.build();
    }

    @Bean
    public LogoutHandler customLogoutHandler() {
        return (request, response, authentication) -> {
            SecurityContextHolder.clearContext();

            //System.out.println("Custom logout handler executed");
        };
    }

}
