package com.ocbc.auctionservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("dev | test | integration-test")
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("pass123"))
                .roles("ADMIN")
                .authorities("ACCESS_USER", "ACCESS_ACCOUNTS", "APPROVE_ACCOUNTS")
                .and()
                .withUser("kaiyang")
                .password(passwordEncoder().encode("pass123"))
                .roles("DEVELOPER")
                .authorities("ACCESS_ACCOUNTS")
                .and()
                .withUser("alex")
                .password(passwordEncoder().encode("pass123"))
                .roles("TESTER")
                .authorities("ACCESS_USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http
                .authorizeRequests()
                .antMatchers("/api/v1/users").authenticated()
                .antMatchers("/api/v1/users").hasAnyRole("ADMIN")
                .antMatchers("/api/v1/accounts/approval/**").hasAuthority("APPROVE_ACCOUNTS")
                .antMatchers("/api/v1/accounts/**").hasAuthority("ACCESS_ACCOUNTS")
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
