package com.czi.scanuvach.config;

import java.util.List;

import javax.sql.DataSource;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableAutoConfiguration
@RequiredArgsConstructor
@EnableScheduling
public class SchedulerConfig {

    private final ApplicationContext applicationContext;

    @Bean(name = "quartzDataSource")
    @QuartzDataSource
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource quartzDataSource() {
        return DataSourceBuilder
                .create(SchedulerConfig.class.getClassLoader())
                .build();
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean(name = "scanuvachScheduler")
    @DependsOn({"flyway"})
    public SchedulerFactoryBean scheduler(List<Trigger> trigger, List<JobDetail> job, DataSource quartzDataSource) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
//        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
        schedulerFactory.setOverwriteExistingJobs(true);
        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(job.toArray(new JobDetail[0]));
        schedulerFactory.setTriggers(trigger.toArray(new Trigger[0]));
        schedulerFactory.setDataSource(quartzDataSource);
        schedulerFactory.setAutoStartup(false);
        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactory.setApplicationContextSchedulerContextKey("applicationContext");

        return schedulerFactory;
    }

}
