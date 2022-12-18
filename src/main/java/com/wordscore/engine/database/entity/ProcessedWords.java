package com.wordscore.engine.database.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
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

    @Column(name = "keywords_count", length = 10)
    private Integer keywordsCount;

    @Column(name = "volume_us")
    private BigDecimal volumeUs;

    @Column(name = "seo_score_us")
    private BigDecimal seoScoreUs;

    @Column(name = "seo_score_uk")
    private BigDecimal seoScoreUk;

    @Column(name = "low_range")
    private BigDecimal lowRange;

    @Column(name = "high_range")
    private BigDecimal highRange;

    @Column(name = "is_com_domain_available")
    private Boolean isComDomainAvailable;

    @Column(name = "is_net_domain_available")
    private Boolean isNetDomainAvailable;

    @Column(name = "is_org_domain_available")
    private Boolean isOrgDomainAvailable;

    @Column(name = "is_trademark")
    private Boolean isTrademark;

    @Column(name = "sold_at")
    private LocalDateTime soldAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
