package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.BlacklistedWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public Optional<BlacklistedWords> findByKeyword(String keyword) {
        return dao.findByKeyword(keyword);
    }

    @Override
    public Optional<BlacklistedWords> save(BlacklistedWords entity) {
        return Optional.of(dao.saveAndFlush(entity));
    }
}
