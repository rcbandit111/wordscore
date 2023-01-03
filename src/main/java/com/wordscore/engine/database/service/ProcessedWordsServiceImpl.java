package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.ProcessedWords;
import com.wordscore.engine.rest.dto.UpdateKeywordRequestDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return dao.updateByKeyword(dto.getKeyword(), dto.getSeoScoreUs(), dto.getKeywordsCount());
    }

    @Override
    public int updateBySeoScoreUs(UpdateKeywordRequestDTO dto) {
        return dao.updateBySeoScoreUs(dto.getKeyword(), dto.getSeoScoreUs(), dto.getLowRange(), dto.getHighRange(), dto.getKeywordsCount());
    }

    @Override
    public Optional<ProcessedWords> findRandomKeyword() {
        return dao.findRandomKeyword();
    }

    @Override
    public Optional<ProcessedWords> findRandomKeywordWhereWordsCountIsEmpty() {
        return dao.findRandomKeywordWhereWordsCountIsEmpty();
    }

    @Override
    public Optional<ProcessedWords> findRandomKeywordWhereTrademarkBlacklistedIsEmpty() {
        return dao.findRandomKeywordWhereTrademarkBlacklistedIsEmpty();
    }

    @Override
    public int updateKeywordCount(Integer count, String keyword) {
        return dao.updateKeywordCount(count, keyword);
    }

    @Override
    public int updateComDomainById(long id, boolean isAvailable) {
        return dao.updateComDomainById(id, isAvailable);
    }

    @Override
    public int updateNetDomainById(long id, boolean isAvailable) {
        return dao.updateNetDomainById(id, isAvailable);
    }

    @Override
    public int updateOrgDomainById(long id, boolean isAvailable) {
        return dao.updateOrgDomainById(id, isAvailable);
    }

    @Override
    public Optional<ProcessedWords> findByKeywordOrderByOldestApiRequestedAt() {
        return dao.findByKeywordOrderByOldestApiRequestedAt();
    }

    @Override
    public int updateTrademarkBlacklisted(long id, String keyword1) {
        return dao.updateTrademarkBlacklisted(id, keyword1);
    }
}
