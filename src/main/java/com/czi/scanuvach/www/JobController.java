package com.czi.scanuvach.www;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class JobController {

    @Autowired
    private Scheduler scheduler;

    @PostMapping("/{jobName}/trigger")
    public String triggerJob(@PathVariable String jobName) {
        try {
            scheduler.triggerJob(JobKey.jobKey(jobName));
//            scheduler.getJobDetail(JobKey.jobKey(jobName)).getKey().getName()
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/" + jobName + "/results";
    }
}
