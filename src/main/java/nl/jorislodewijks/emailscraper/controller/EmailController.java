package nl.jorislodewijks.emailscraper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.jorislodewijks.emailscraper.model.EmailAddress;
import nl.jorislodewijks.emailscraper.service.EmailService;

@RestController
@RequestMapping("api/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping
    public List<EmailAddress> getEmails() {
        return emailService.getAll();
    }

}
