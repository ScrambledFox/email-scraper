package nl.jorislodewijks.emailscraper.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.util.concurrent.RateLimiter;

@Configuration
public class RateLimitConfig {

    @Value("${api.rate-limit}")
    private double rateLimit;

    @Bean
    public RateLimiter rateLimiter() {
        // Only allows {api.rate-limit} requests per second, set in
        // application.properties
        return RateLimiter.create(rateLimit);
    }

}
