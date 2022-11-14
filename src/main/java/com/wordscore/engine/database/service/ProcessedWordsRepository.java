package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.ProcessedWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProcessedWordsRepository extends JpaRepository<ProcessedWords, Integer>, JpaSpecificationExecutor<ProcessedWords> {

    @Query(value = "select * from processed_words pw where pw.keyword = :keyword ORDER BY pw.created_at ASC", nativeQuery = true)
    Optional<ProcessedWords> findByKeyword(@Param("keyword") String keyword);

    @Modifying
    @Query(value="update processed_words set seo_score_us = :seoScoreUs where keyword = :keyword", nativeQuery = true)
    int updateByKeyword(@Param("keyword") String keyword,
                        @Param("seoScoreUs") BigDecimal seoScoreUs);

    @Modifying
    @Query(value="update processed_words set seo_score_us = :seoScoreUs, low_range = :lowRange, high_range = :highRange where keyword = :keyword", nativeQuery = true)
    int updateBySeoScoreUs(@Param("keyword") String keyword,
                        @Param("seoScoreUs") BigDecimal seoScoreUs,
                        @Param("lowRange") BigDecimal lowRange,
                        @Param("highRange") BigDecimal highRange);

}
