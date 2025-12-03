package com.metaxiii.fr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfigurer {

  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http) {
    return http
      .authorizeHttpRequests(cust -> cust.requestMatchers("/private/**").hasRole("USER"))
      .authorizeHttpRequests(cust -> cust.requestMatchers("/public/**").permitAll())
      .authorizeHttpRequests(cust -> cust.anyRequest().authenticated())
      .httpBasic(Customizer.withDefaults())
      .build();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @SuppressWarnings("all")
  public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails user = User.withUsername("spring").password(passwordEncoder.encode("secret")).roles("USER").build();
    return new InMemoryUserDetailsManager(user);
  }
}
