package nl.jorislodewijks.emailscraper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.util.concurrent.RateLimiter;

@Configuration
public class RateLimitConfig {

    @Bean
    public RateLimiter rateLimiter() {
        // Only allow 1 request per second
        return RateLimiter.create(1.0);
    }

}
