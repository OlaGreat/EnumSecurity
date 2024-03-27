package Enum.security;

import Enum.data.models.User;
import Enum.security.filters.EnumAuthenticationFilter;
import Enum.security.filters.EnumAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static Enum.data.models.Role.ADMIN;
import static Enum.data.models.Role.CUSTOMER;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;


    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity httpSecurity) throws Exception {
        final String[] publicEndPoints ={"/api/v1/enum", "/login"};
        final String[] adminEndPoints ={"/api/v1/admin/**"};
        return httpSecurity
                .addFilterAt(new EnumAuthenticationFilter(authenticationManager),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new EnumAuthorizationFilter(), EnumAuthenticationFilter.class)
                .sessionManagement(customizer->customizer.sessionCreationPolicy(STATELESS))
                .csrf(c->c.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(c->c.requestMatchers(POST, publicEndPoints).permitAll())
                .authorizeHttpRequests(c->c.requestMatchers(adminEndPoints).hasAuthority(ADMIN.name()))
                .authorizeHttpRequests(c->c.anyRequest().hasAuthority(CUSTOMER.name()))
                .build();


    }

}
