package com.wordscore.engine.processor;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.List;

@Configuration
public class SchedulerConfig {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerConfig.class);

    private ApplicationContext applicationContext;

    @Autowired
    public SchedulerConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public JobFactory jobFactory() {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(List<JobDetail> jobDetails,
                                                     List<Trigger> triggers) {

        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        LOG.debug("Setting the jobs Scheduler up");
        schedulerFactory.setJobFactory(jobFactory());
        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactory.setAutoStartup(true);
        // Multiple jobs are passed as an array
        schedulerFactory.setJobDetails(jobDetails.toArray(JobDetail[] ::new));
        // Multiple triggers are passed as an array
        schedulerFactory.setTriggers(triggers.toArray(Trigger[] ::new));
        return schedulerFactory;
    }

    // Job for checking .com domains

    @Bean(name = "DomainComCheckJob")
    public JobDetailFactoryBean DomainComCheckJob() {

        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(DomainComCheckJob.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean domainComCheckJobTrigger(@Qualifier("DomainComCheckJob") JobDetail job,
                                                    @Value("${first.job.frequency}") long frequency) {

        LOG.info("Domain Com Job Trigger");

        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(job);
        factoryBean.setStartDelay(0L);
        factoryBean.setRepeatInterval(frequency);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return factoryBean;
    }

    // Job for checking .net domains

    @Bean(name = "DomainNetCheck")
    public JobDetailFactoryBean DomainNetCheck() {

        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(DomainNetCheckJob.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean domainNetCheckTrigger(@Qualifier("DomainNetCheck") JobDetail job,
                                                     @Value("${second.job.frequency}") long frequency) {

        LOG.info("Domain Net Job Trigger");

        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(job);
        factoryBean.setStartDelay(0L);
        factoryBean.setRepeatInterval(frequency);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return factoryBean;
    }

    // Job for checking .org domains

    @Bean(name = "DomainOrgCheck")
    public JobDetailFactoryBean domainOrgCheck() {

        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(DomainOrgCheckJob.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean domainOrgTrigger(@Qualifier("DomainOrgCheck") JobDetail job,
                                                     @Value("${third.job.frequency}") long frequency) {

        LOG.info("Domain Org Job Trigger");

        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(job);
        factoryBean.setStartDelay(0L);
        factoryBean.setRepeatInterval(frequency);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return factoryBean;
    }

    // Job for counting words

    @Bean(name = "WordsCountJob")
    public JobDetailFactoryBean wordsCountJob() {

        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(WordsCountJob.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean wordsCountJobTrigger(@Qualifier("WordsCountJob") JobDetail job,
                                                    @Value("${third.job.frequency}") long frequency) {

        LOG.info("Words Count Job Trigger");

        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(job);
        factoryBean.setStartDelay(0L);
        factoryBean.setRepeatInterval(frequency);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return factoryBean;
    }
}
