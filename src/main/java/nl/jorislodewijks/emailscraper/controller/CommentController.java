package nl.jorislodewijks.emailscraper.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import nl.jorislodewijks.emailscraper.model.Comment;
import nl.jorislodewijks.emailscraper.model.EmailAddress;
import nl.jorislodewijks.emailscraper.repository.CommentRepository;
import nl.jorislodewijks.emailscraper.repository.EmailRepository;

@RestController
@RequestMapping
public class CommentController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${post.api.url}")
    private String baseApiUrl;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private CommentRepository commentRepository;

    // #region Endpoints for external API
    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getComments(@PathVariable Long postId) {
        String url = baseApiUrl + "/" + postId + "/comments";
        ResponseEntity<Comment[]> responseEntity = restTemplate.getForEntity(url, Comment[].class);
        List<Comment> comments = Arrays.asList(responseEntity.getBody());

        // Extract and save email addresses
        comments.forEach(comment -> {
            EmailAddress email = new EmailAddress(comment.getEmail());

            if (emailRepository.existsByAddress(email.getAddress())) {
                return;
            }

            emailRepository.save(email);
        });

        // Save comments
        commentRepository.saveAll(comments);

        return comments;
    }
    // #endregion

    // #region API endpoints
    @GetMapping("/api/comments")
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    @GetMapping("api/emails/{emailAddress}/comments")
    public List<Comment> getCommentsByEmailAddress(@PathVariable String emailAddress) {
        List<Comment> comments = commentRepository.findAllByEmail(emailAddress);
        return comments;
    }
    // #endregion
}
