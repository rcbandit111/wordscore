package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.BlacklistResult;
import com.wordscore.engine.database.entity.BlacklistedWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlacklistedWordsRepository extends JpaRepository<BlacklistedWords, Integer>, JpaSpecificationExecutor<BlacklistedWords> {

    @Query(value = "select * from blacklisted_words pw where pw.keyword = :keyword ORDER BY pw.created_at ASC", nativeQuery = true)
    Optional<BlacklistedWords> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "select w.id, t.keyword from processed_words w inner join blacklisted_words t on t.keyword ilike '%'||w.keyword||'%' where w.keyword = :keyword", nativeQuery = true)
    List<BlacklistResult> findBlacklistedKeyword(@Param("keyword") String keyword);
}
