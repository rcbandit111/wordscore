package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.BlacklistResult;
import com.wordscore.engine.database.entity.BlacklistedWords;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BlacklistedWordsServiceImpl implements BlacklistedWordsService {

    // SELECT pg_size_pretty(pg_database_size('wordscore_engine'));

    @PersistenceContext
    private EntityManager entityManager;

    private BlacklistedWordsRepository dao;

    @Autowired
    public BlacklistedWordsServiceImpl(BlacklistedWordsRepository dao){
        this.dao = dao;
    }

    @Override
    public List<BlacklistResult> findBlacklistedKeyword(String keyword) {
        return dao.findBlacklistedKeyword(keyword);
    }

    @Override
    public Optional<BlacklistedWords> findByKeyword(String keyword) {
        return dao.findByKeyword(keyword);
    }

    @Override
    public Optional<BlacklistedWords> save(BlacklistedWords entity) {
        return Optional.of(dao.saveAndFlush(entity));
    }

    @Override
    @Cacheable("blacklist")
    public List<BlacklistedWords> findAll() {
        return dao.findAll();
    }
}
