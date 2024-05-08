package com.tunemerge.tunemerge.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Entity
@Repository
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String displayName;
    private String email;
    private String href;
    private String spotifyId;
    private String uri;
}