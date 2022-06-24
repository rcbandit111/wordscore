package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.BlacklistedWords;

import java.util.Optional;

public interface BlacklistedWordsService {

    Optional<BlacklistedWords> findByKeyword(String keyword);

    Optional<BlacklistedWords> save(BlacklistedWords entity);
}
