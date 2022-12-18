package com.wordscore.engine.processor;

import com.wordscore.engine.database.service.ProcessedWordsService;
import com.wordscore.engine.service.generator.WordsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
abstract public class ServiceFactory {

    @Autowired
    protected WordsGenerator wordsGenerator;

    @Autowired
    protected ProcessedWordsService processedWordsService;
}
