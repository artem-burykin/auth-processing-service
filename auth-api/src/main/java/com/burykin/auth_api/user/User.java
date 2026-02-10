package com.burykin.auth_api.user;

import com.burykin.auth_api.processing.Processing;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Processing> processings;

    protected User() {
        // JPA only
    }

    public User(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<Processing> getProcessings() {
        return Collections.unmodifiableList(processings);
    }

    public void addProcessing(Processing processing) {
        processings.add(processing);
    }

    public void removeProcessing(Processing processing) {
        processings.remove(processing);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
