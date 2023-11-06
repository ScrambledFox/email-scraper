package nl.jorislodewijks.emailscraper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.jorislodewijks.emailscraper.model.EmailAddress;
import nl.jorislodewijks.emailscraper.repository.EmailRepository;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    public List<EmailAddress> getAll() {
        return emailRepository.findAll();
    }

    public boolean existsByAddress(String address) {
        return emailRepository.existsByAddress(address);
    }

}
