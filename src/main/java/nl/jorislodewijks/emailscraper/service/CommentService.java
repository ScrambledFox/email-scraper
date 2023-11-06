package nl.jorislodewijks.emailscraper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.jorislodewijks.emailscraper.model.Comment;
import nl.jorislodewijks.emailscraper.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByEmailAddress(String email) {
        return commentRepository.findAllByEmail(email);
    }

}
