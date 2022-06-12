package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.ProcessedWords;
import com.wordscore.engine.rest.dto.UpdateKeywordRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Service
@Transactional
public class ProcessedWordsServiceImpl implements ProcessedWordsService {

    // SELECT pg_size_pretty(pg_database_size('wordscore_engine'));

    @PersistenceContext
    private EntityManager entityManager;

    private ProcessedWordsRepository dao;

    @Autowired
    public ProcessedWordsServiceImpl(ProcessedWordsRepository dao){
        this.dao = dao;
    }

    @Override
    public Optional<ProcessedWords> findByKeyword(String keyword) {
        return dao.findByKeyword(keyword);
    }

    @Override
    public Optional<ProcessedWords> save(ProcessedWords entity) {
        return Optional.of(dao.saveAndFlush(entity));
    }

    @Override
    public int update(UpdateKeywordRequestDTO dto) {
        return dao.updateByKeyword(dto.getKeyword(), dto.getVolumeUs(), dto.getSeoScoreUs(), dto.getSeoScoreUk());
    }
}
