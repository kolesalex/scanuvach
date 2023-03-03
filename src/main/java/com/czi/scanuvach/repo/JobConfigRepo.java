package com.czi.scanuvach.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.czi.scanuvach.model.JobConfigItem;

public interface JobConfigRepo extends JpaRepository<JobConfigItem, Long> {
    List<JobConfigItem> findByJobName(String job);
}
