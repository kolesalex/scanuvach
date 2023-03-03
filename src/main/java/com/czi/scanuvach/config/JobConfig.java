package com.czi.scanuvach.config;

import static com.czi.scanuvach.util.JobConfigurationUtils.createJobDetail;
import static com.czi.scanuvach.util.JobConfigurationUtils.extractJobName;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import com.czi.scanuvach.job.NmapPortsJob;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class JobConfig {

    @Bean(name = "nmapPortsJobDetail")
    public JobDetailFactoryBean nmapJobDetail() {
        return createJobDetail(NmapPortsJob.class, extractJobName(NmapPortsJob.class));
    }

//    @Bean
//    public CronTriggerFactoryBean nmapJobTrigger(@Qualifier("nmapPortsJobDetail") JobDetail jobDetail) {
//        return createCronTrigger(jobDetail,
//                "0 0/5 * * * ?",
//                extractJobTriggerName(NmapPortsJob.class));
//    }
}
