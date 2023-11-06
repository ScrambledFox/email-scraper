package nl.jorislodewijks.emailscraper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.jorislodewijks.emailscraper.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    public List<Comment> findAllByEmail(String email);

}
