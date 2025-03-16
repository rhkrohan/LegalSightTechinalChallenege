package speechmanager;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Speech {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Full text of the speech
    @Column(columnDefinition = "TEXT")
    private String text;

    // Author of the speech
    private String author;
    
    // Comma-separated keywords for the speech
    private String keywords;
    
    // Date when the speech was delivered or drafted
    private LocalDate speechDate;

    // Default constructor required by JPA
    public Speech() {}

    // Parameterized constructor for convenience
    public Speech(String text, String author, String keywords, LocalDate speechDate) {
        this.text = text;
        this.author = author;
        this.keywords = keywords;
        this.speechDate = speechDate;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public LocalDate getSpeechDate() {
        return speechDate;
    }

    public void setSpeechDate(LocalDate speechDate) {
        this.speechDate = speechDate;
    }
}
