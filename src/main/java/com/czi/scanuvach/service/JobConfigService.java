package com.czi.scanuvach.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czi.scanuvach.model.JobConfigItem;
import com.czi.scanuvach.repo.JobConfigRepo;

@Service
public class JobConfigService {

    @Autowired
    private JobConfigRepo jobConfigRepo;

    public Map<String,String> getPropertiesByJobName(String jobName){
        return jobConfigRepo.findByJobName(jobName)
                .stream()
                .collect(Collectors.toMap(JobConfigItem::getKey, JobConfigItem::getValue));
    }
}
