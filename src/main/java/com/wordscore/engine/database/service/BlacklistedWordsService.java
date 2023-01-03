package com.wordscore.engine.database.service;

import com.wordscore.engine.database.entity.BlacklistResult;
import com.wordscore.engine.database.entity.BlacklistedWords;

import java.util.List;
import java.util.Optional;

public interface BlacklistedWordsService {

    List<BlacklistResult> findBlacklistedKeyword(String keyword);

    Optional<BlacklistedWords> findByKeyword(String keyword);

    Optional<BlacklistedWords> save(BlacklistedWords entity);
}
