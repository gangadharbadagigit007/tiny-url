package com.gb.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "short_urls")
@Data
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 32)
    private String code;

    @Column(name = "target_url", nullable = false, length = 2048)
    private String targetUrl;

    private Instant expiresAt;

    private long hits = 0;

    private Instant lastAccessedAt;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}
