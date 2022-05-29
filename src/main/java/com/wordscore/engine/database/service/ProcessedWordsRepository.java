package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.ProcessedWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedWordsRepository extends JpaRepository<ProcessedWords, Integer>, JpaSpecificationExecutor<ProcessedWords> {
}
