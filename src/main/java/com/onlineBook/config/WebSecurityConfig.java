package com.onlineBook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private final UserDetailsService userDetailsService;

  public WebSecurityConfig(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  // @Bean
  // public InMemoryUserDetailsManager userDetailsManager() {
  // UserDetails user1 = User.withUsername("user1")
  // .password(passwordEncoder().encode("user1"))
  // .roles("USER")
  // .build();
  // UserDetails user2 = User.withUsername("user2")
  // .password(passwordEncoder().encode("user2"))
  // .roles("ADMIN")
  // .build();
  // return new InMemoryUserDetailsManager(user1, user2);
  // }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/authors/**")
            .hasAnyAuthority("ADMIN", "USER").requestMatchers(HttpMethod.GET, "/books/**")
            .hasAnyAuthority("ADMIN", "USER").requestMatchers(HttpMethod.GET, "/loginSuccess").authenticated()
            .requestMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().permitAll())
        .formLogin(form -> form.loginPage("/loginPage").loginProcessingUrl("/login").defaultSuccessUrl("/loginSuccess"))
        .logout(logout -> logout.logoutSuccessUrl("/")).build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(this.userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
