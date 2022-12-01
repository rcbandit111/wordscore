package com.wordscore.engine.processor;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

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

//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(Trigger simpleJobTrigger) throws IOException {
//
//        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
//        schedulerFactory.setQuartzProperties(quartzProperties());
//        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);
//        schedulerFactory.setAutoStartup(true);
//        schedulerFactory.setTriggers(simpleJobTrigger);
//        schedulerFactory.setJobFactory(jobFactory());
//        return schedulerFactory;
//    }
//
//    @Bean
//    public SimpleTriggerFactoryBean simpleJobTrigger(@Qualifier("keywordPostJobDetail") JobDetail jobDetail,
//                                                     @Value("${simplejob.frequency}") long frequency) {
//        LOG.info("simpleJobTrigger");
//
//        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
//        factoryBean.setJobDetail(jobDetail);
//        factoryBean.setStartDelay(0L);
//        factoryBean.setRepeatInterval(frequency);
//        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY); // factoryBean.setRepeatCount(0); //
//        return factoryBean;
//    }
//
//    @Bean
//    public JobDetailFactoryBean keywordPostJobDetail() {
//        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
//        factoryBean.setJobClass(ImportCsvFilePostJob.class);
////        factoryBean.setJobClass(DomainNetCheckJob.class);
//        factoryBean.setDurability(true);
//        return factoryBean;
//    }
//
//    public Properties quartzProperties() throws IOException {
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
//        propertiesFactoryBean.afterPropertiesSet();
//        return propertiesFactoryBean.getObject();
//    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(List<JobDetail> jobDetails,
                                                     List<Trigger> triggers) {

        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        LOG.debug("Setting the jobs Scheduler up");
        schedulerFactory.setJobFactory(jobFactory());
        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactory.setAutoStartup(true);
        //Multiple jobs are passed as an array
        schedulerFactory.setJobDetails(jobDetails.toArray(JobDetail[] ::new));
        //Multiple triggers are passed as an array
        schedulerFactory.setTriggers(triggers.toArray(Trigger[] ::new));
        return schedulerFactory;
    }


    @Bean(name = "FirstJob")
    public JobDetailFactoryBean firstJob() {

        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(DomainComCheckJob.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean firstJobTrigger(@Qualifier("FirstJob") JobDetail job,
                                                    @Value("${first.job.frequency}") long frequency) {

        LOG.info("First Job Trigger");

        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(job);
        factoryBean.setStartDelay(0L);
        factoryBean.setRepeatInterval(frequency);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return factoryBean;
    }

    @Bean(name = "SecondJob")
    public JobDetailFactoryBean secondJob() {

        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(DomainNetCheckJob.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean secondJobTrigger(@Qualifier("SecondJob") JobDetail job,
                                                     @Value("${second.job.frequency}") long frequency) {

        LOG.info("Second Job Trigger");

        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(job);
        factoryBean.setStartDelay(0L);
        factoryBean.setRepeatInterval(frequency);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return factoryBean;
    }

    @Bean(name = "ThirdJob")
    public JobDetailFactoryBean thirdJob() {

        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(DomainOrgCheckJob.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SimpleTriggerFactoryBean thirdJobTrigger(@Qualifier("ThirdJob") JobDetail job,
                                                     @Value("${third.job.frequency}") long frequency) {

        LOG.info("Third Job Trigger");

        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(job);
        factoryBean.setStartDelay(0L);
        factoryBean.setRepeatInterval(frequency);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return factoryBean;
    }
}
