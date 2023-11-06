package nl.jorislodewijks.emailscraper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.jorislodewijks.emailscraper.model.EmailAddress;

@Repository
public interface EmailRepository extends JpaRepository<EmailAddress, Long> {

    boolean existsByAddress(String address);

}
