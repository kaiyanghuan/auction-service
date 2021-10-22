package com.auction.service.configurations

import com.auction.service.authentication.jwt.JwtAuthenticationEntryPoint
import com.auction.service.authentication.jwt.JwtTokenAuthenticationFilter
import com.auction.service.authentication.jwt.UserPrincipalDetailsService
import com.auction.service.logging.LoggingDiagnosticFilter
import org.aspectj.weaver.tools.cache.SimpleCacheFactory.path
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.access.ExceptionTranslationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@Profile("!dev & !test & !integration-test")
open class WebSecurityConfiguration(
        private val jwtTokenAuthenticationFilter: JwtTokenAuthenticationFilter,
        private val unauthorizedHandler: JwtAuthenticationEntryPoint,
        private val userPrincipalDetailsService: UserPrincipalDetailsService,
        @Value("\${app.auth.freeUrl}") val path: Array<String>
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .cors().disable()
                .addFilterAt(LoggingDiagnosticFilter(), ExceptionTranslationFilter::class.java)
                .addFilterAt(jwtTokenAuthenticationFilter, LoggingDiagnosticFilter::class.java)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests().antMatchers(*path).permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(daoAuthenticationProvider())
    }

    @Bean
    open fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService)
        return daoAuthenticationProvider
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

}