package com.springboot.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
           .authorizeRequests()
           .antMatchers("/api/admin").hasRole("ADMIN")
           .anyRequest().permitAll()
           .and().httpBasic().and().csrf().disable();
           
   }
   @Override
   public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
           .withUser("likki")
           .password(bCryptPasswordEncoder().encode("likki123"))
           .roles("ADMIN");
   }
   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

   
}
