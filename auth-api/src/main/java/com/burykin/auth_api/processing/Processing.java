package com.burykin.auth_api.processing;

import com.burykin.auth_api.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "processings")
public class Processing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String inputText;
    @Column(name = "output_text", nullable = false)
    private String outputText;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Processing() {
        // JPA only
    }

    public Processing(String inputText, String outputText, User user) {
        this.inputText = inputText;
        this.outputText = outputText;
        this.user = user;
    }

    public String getInputText() {
        return inputText;
    }

    public String getOutputText() {
        return outputText;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Processing processing)) return false;
        return id != null && Objects.equals(id, processing.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Processing{" +
                "inputText='" + inputText + '\'' +
                ", outputText='" + outputText + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                '}';
    }
}
