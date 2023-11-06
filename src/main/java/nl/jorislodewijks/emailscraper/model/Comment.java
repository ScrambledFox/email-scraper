package nl.jorislodewijks.emailscraper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

    private Long postId;

    @Id
    private Long id;
    private String name;
    private String email;
    private String body;

    // #region Getters and Setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long PostId) {
        this.postId = PostId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String Body) {
        this.body = Body;
    }
    // #endregion
}
