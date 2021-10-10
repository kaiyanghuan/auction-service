package com.auction.service.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@Profile("dev | test | integration-test")
class DevSecurityConfiguration : WebSecurityConfigurerAdapter() {


    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
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
                .authorities("ACCESS_USER")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/health/**").permitAll()
                .antMatchers("/api/v1/users").authenticated()
                .antMatchers("/api/v1/users").hasAnyRole("ADMIN")
                .antMatchers("/api/v1/accounts/approval/**").hasAuthority("APPROVE_ACCOUNTS")
                .antMatchers("/api/v1/accounts/**").hasAuthority("ACCESS_ACCOUNTS")
                .and()
                .httpBasic()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
