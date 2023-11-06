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

import nl.jorislodewijks.emailscraper.model.Post;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${post.api.url}")
    private String baseApiUrl;

    @GetMapping
    public List<Post> getPosts() {
        ResponseEntity<Post[]> responseEntity = restTemplate.getForEntity(baseApiUrl, Post[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        String url = baseApiUrl + "/" + postId;
        ResponseEntity<Post> responseEntity = restTemplate.getForEntity(url, Post.class);
        return responseEntity.getBody();
    }

}
