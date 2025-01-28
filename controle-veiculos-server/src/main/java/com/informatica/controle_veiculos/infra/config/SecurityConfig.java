package com.informatica.controle_veiculos.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.informatica.controle_veiculos.data.exception.UnauthorizedException;
import com.informatica.controle_veiculos.infra.filter.NotFoundFilter;
import com.informatica.controle_veiculos.infra.filter.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  SecurityFilter securityFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
    try {
      httpSecurity
          .authorizeHttpRequests(authorize -> authorize
              .requestMatchers(HttpMethod.POST, "/api/v1/admins").hasAuthority("ADMIN")
              .requestMatchers(HttpMethod.GET, "/api/v1/admins").hasAuthority("ADMIN")
              .requestMatchers(HttpMethod.POST, "/api/v1/auth").permitAll()
              .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
              .requestMatchers(HttpMethod.GET, "/api/v1/users").permitAll()
              .requestMatchers(HttpMethod.POST, "/api/v1/vehicles").permitAll()
              .requestMatchers(HttpMethod.GET, "/api/v1/vehicles").permitAll()
              .requestMatchers(HttpMethod.POST, "/api/v1/stamps").permitAll()
              .requestMatchers(HttpMethod.GET, "/api/v1/stamps").permitAll()
              .requestMatchers(HttpMethod.POST, "/api/v1/docs").permitAll()
              .anyRequest().permitAll())
          .cors(Customizer.withDefaults())
          .csrf(csrf -> csrf.disable())
          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
          .addFilterAfter(new NotFoundFilter(), SecurityFilter.class);

      return httpSecurity.build();
    } catch (Exception e) {
      throw new UnauthorizedException();
    }
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean("bCryptPasswordEncoder")
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
