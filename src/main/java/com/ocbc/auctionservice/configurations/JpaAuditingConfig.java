package com.ocbc.auctionservice.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

    @Autowired
    private RequestSynchronizationManager requestSynchronizationManager;

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new LoggedInUsernameAuditorAware();
    }

    public class LoggedInUsernameAuditorAware implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            return Optional.of(requestSynchronizationManager.loggedInUsername());
        }
    }
}
