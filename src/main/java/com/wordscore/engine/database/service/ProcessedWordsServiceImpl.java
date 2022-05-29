package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.ProcessedWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Service
@Transactional
public class ProcessedWordsServiceImpl implements ProcessedWordsService {

    @PersistenceContext
    private EntityManager entityManager;

    private ProcessedWordsRepository dao;

    @Autowired
    public ProcessedWordsServiceImpl(ProcessedWordsRepository dao){
        this.dao = dao;
    }

    @Override
    public Optional<ProcessedWords> findByName(String name) {
        String hql = "select e from " + ProcessedWords.class.getName() + " e where e.name = :name ORDER BY e.createdAt ASC";
        TypedQuery<ProcessedWords> query = entityManager.createQuery(hql, ProcessedWords.class).setMaxResults(1).setParameter("name", name);
        Optional<ProcessedWords> word = Optional.of(query.getSingleResult());
        return word;
    }

    @Override
    public Optional<ProcessedWords> findByGoogleScore(long googleScore) {
        String hql = "select e from " + ProcessedWords.class.getName() + " e where e.google_score = :googleScore ORDER BY e.createdAt ASC";
        TypedQuery<ProcessedWords> query = entityManager.createQuery(hql, ProcessedWords.class).setMaxResults(1).setParameter("googleScore", googleScore);
        Optional<ProcessedWords> word = Optional.of(query.getSingleResult());
        return word;
    }

    @Override
    public void save(ProcessedWords entity) {
        dao.saveAndFlush(entity);
    }
}
