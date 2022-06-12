package com.wordscore.engine.database.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "processed_words")
public class ProcessedWords implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private long id;

    @Column(name = "keyword", length = 200, unique = true)
    private String keyword;

    @Column(name = "volume_us", length = 100)
    private long volumeUs;

    @Column(name = "seo_score_us", length = 100)
    private long seoScoreUs;

    @Column(name = "seo_score_uk", length = 100)
    private long seoScoreUk;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
