package com.flightmanager.FlightBookingService.configuration;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryConfiguration {



    @Bean
    public Retry userServiceRetry() {
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(5)
                .waitDuration(java.time.Duration.ofMillis(5000))
                .build();
        RetryRegistry retryRegistry = RetryRegistry.of(retryConfig);
        return retryRegistry.retry("userServiceRetry");
    }
}
