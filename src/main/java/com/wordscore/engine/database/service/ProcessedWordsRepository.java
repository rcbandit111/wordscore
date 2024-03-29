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
    @Query(value="update processed_words set seo_score_us = :seoScoreUs, keywords_count = :keywordsCount where keyword = :keyword", nativeQuery = true)
    int updateByKeyword(@Param("keyword") String keyword,
                        @Param("seoScoreUs") BigDecimal seoScoreUs,
                        @Param("keywordsCount") Integer keywordsCount);

    @Modifying
    @Query(value="update processed_words set seo_score_us = :seoScoreUs, low_range = :lowRange, high_range = :highRange, keywords_count = :keywordsCount where keyword = :keyword", nativeQuery = true)
    int updateBySeoScoreUs(@Param("keyword") String keyword,
                        @Param("seoScoreUs") BigDecimal seoScoreUs,
                        @Param("lowRange") BigDecimal lowRange,
                        @Param("highRange") BigDecimal highRange,
                        @Param("keywordsCount") Integer keywordsCount);

    @Query(value = "select * from processed_words pw TABLESAMPLE BERNOULLI(1) LIMIT 1", nativeQuery = true)
    Optional<ProcessedWords> findRandomKeyword();

    @Query(value = "select * from processed_words pw TABLESAMPLE BERNOULLI(1) where keywords_count is null LIMIT 1", nativeQuery = true)
    Optional<ProcessedWords> findRandomKeywordWhereWordsCountIsEmpty();

//    @Query(value = "select * from processed_words pw TABLESAMPLE BERNOULLI(1) LIMIT 1", nativeQuery = true)
    @Query(value = "select id, keyword, seo_score_us, is_com_domain_available, low_range, api_requested_at, created_at, high_range, is_net_domain_available, is_org_domain_available, keywords_count, seo_score_uk, sold_at, trademark_blacklisted, updated_at, volume_us from processed_words pw TABLESAMPLE BERNOULLI(1) where seo_score_us > 1000 and is_com_domain_available = 'true' and low_range is not null LIMIT 1", nativeQuery = true)
    Optional<ProcessedWords> findRandomKeywordWhereTrademarkBlacklistedIsEmpty();

    @Modifying
    @Query(value = "UPDATE processed_words SET keywords_count = :count WHERE keyword = :keyword", nativeQuery = true)
    int updateKeywordCount(@Param("count") Integer count, @Param("keyword") String keyword);

    @Modifying
    @Query(value="update processed_words set is_com_domain_available = :isAvailable where id = :id", nativeQuery = true)
    int updateComDomainById(@Param("id") long id, @Param("isAvailable") boolean isAvailable);

    @Modifying
    @Query(value="update processed_words set is_com_domain_available = :isAvailable where keyword = :keyword", nativeQuery = true)
    int updateComDomainByKeyword(@Param("keyword") String keyword, @Param("isAvailable") boolean isAvailable);

    @Modifying
    @Query(value="update processed_words set is_net_domain_available = :isAvailable where id = :id", nativeQuery = true)
    int updateNetDomainById(@Param("id") long id, @Param("isAvailable") boolean isAvailable);

    @Modifying
    @Query(value="update processed_words set is_net_domain_available = :isAvailable where keyword = :keyword", nativeQuery = true)
    int updateNetDomainByKeyword(@Param("keyword") String keyword, @Param("isAvailable") boolean isAvailable);

    @Modifying
    @Query(value="update processed_words set is_org_domain_available = :isAvailable where id = :id", nativeQuery = true)
    int updateOrgDomainById(@Param("id") long id, @Param("isAvailable") boolean isAvailable);

    @Modifying
    @Query(value="update processed_words set is_org_domain_available = :isAvailable where keyword = :keyword", nativeQuery = true)
    int updateOrgDomainByKeyword(@Param("keyword") String keyword, @Param("isAvailable") boolean isAvailable);

    @Query(value = "select id, processed_words, api_requested_at from processed_words ORDER BY api_requested_at ASC LIMIT 1", nativeQuery = true)
    Optional<ProcessedWords> findByKeywordOrderByOldestApiRequestedAt();

    @Modifying
    @Query(value="update processed_words set seo_score_us = :seoScoreUs, keywords_count = :keywordsCount where id = :id", nativeQuery = true)
    int updateOldestApiRequestedAtById(@Param("id") Long id);

    @Modifying
    @Query(value="update processed_words set trademark_blacklisted = :foundBlacklistedKeyword where id = :id", nativeQuery = true)
    int updateTrademarkBlacklistedById(@Param("id") Long id, @Param("foundBlacklistedKeyword") String foundBlacklistedKeyword);

    @Modifying
    @Query(value="update processed_words set trademark_blacklisted = :foundBlacklistedKeyword where keyword = :keyword", nativeQuery = true)
    int updateTrademarkBlacklistedByKeyword(@Param("keyword") String keyword, @Param("foundBlacklistedKeyword") String foundBlacklistedKeyword);
}
