package nl.jorislodewijks.emailscraper.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import nl.jorislodewijks.emailscraper.model.Comment;
import nl.jorislodewijks.emailscraper.model.EmailAddress;
import nl.jorislodewijks.emailscraper.repository.EmailRepository;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${post.api.url}")
    private String baseApiUrl;

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping
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

        return comments;
    }

}
