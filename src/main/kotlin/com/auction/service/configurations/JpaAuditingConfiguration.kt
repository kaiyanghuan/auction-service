package com.auction.service.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@Configuration
@EnableJpaAuditing
open class JpaAuditingConfiguration{

    @Bean
    open fun auditorProvider(): AuditorAware<String> {
        return LoggedInUsernameAuditorAware()
    }
}

class LoggedInUsernameAuditorAware: AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of("System")
    }
}