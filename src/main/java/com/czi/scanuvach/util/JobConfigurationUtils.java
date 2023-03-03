package com.czi.scanuvach.util;

import java.beans.Introspector;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class JobConfigurationUtils {

    public static CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronExpression, String triggerName) {
        log.info("Create Cron Trigger (jobDetail={}, cronExpression={}, triggerName={})", jobDetail.toString(), cronExpression, triggerName);
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setName(triggerName);
        factoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
        return factoryBean;
    }

    public static JobDetailFactoryBean createJobDetail(Class jobClass, String jobName) {
        log.info("Create Job Detail (jobClass={}, jobName={})", jobClass.getName(), jobName);
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setName(jobName);
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(false);
        return factoryBean;
    }

    public static String extractJobName(Class clazz) {
        return Introspector.decapitalize(clazz.getSimpleName());
    }

    public static String extractJobTriggerName(Class clazz) {
        return extractJobName(clazz) + "Trigger";
    }


}
