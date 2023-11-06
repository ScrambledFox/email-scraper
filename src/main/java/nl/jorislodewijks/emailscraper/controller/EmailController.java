package nl.jorislodewijks.emailscraper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.google.common.util.concurrent.RateLimiter;

import nl.jorislodewijks.emailscraper.model.EmailAddress;
import nl.jorislodewijks.emailscraper.service.EmailService;

@RestController
@RequestMapping("api/emails")
public class EmailController {

    @Autowired
    private RateLimiter rateLimiter;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public List<EmailAddress> getEmails() {
        if (rateLimiter.tryAcquire() == false) {
            throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
        }

        return emailService.getAll();
    }

}
