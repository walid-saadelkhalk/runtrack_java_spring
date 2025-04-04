package com.example.jour4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // ✅ Public : login et console H2
                        .requestMatchers("/", "/login", "/h2-console/**").permitAll()

                        // ✅ ADMIN uniquement
                        .requestMatchers("/form", "/add").hasRole("ADMIN")

                        // ✅ Tout utilisateur connecté
                        .requestMatchers("/persons", "/export").authenticated()

                        // ❌ Refuser tout le reste
                        .anyRequest().denyAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/persons", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        // ✅ H2 console support (frames)
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    // ✅ Deux utilisateurs (admin + user)
    @Bean
    public UserDetailsService userDetailsService() {
        var admin = User.withUsername("admin")
                .password("1234")
                .roles("ADMIN")
                .build();

        var user = User.withUsername("user")
                .password("1234")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    // ✅ NoOp : mot de passe non chiffré (pour tests uniquement)
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
