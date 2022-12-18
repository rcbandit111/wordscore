package com.wordscore.engine.processor;

import com.wordscore.engine.database.entity.ProcessedWords;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Optional;

public class BlacklistWordCheckJob extends ServiceFactory implements Job {

    public BlacklistWordCheckJob() {
    }

    @Override
    public void execute(JobExecutionContext context) {

        // TODO
    }
}
