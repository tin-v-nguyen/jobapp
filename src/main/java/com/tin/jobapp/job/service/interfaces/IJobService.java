package com.tin.jobapp.job.service.interfaces;

import com.tin.jobapp.job.model.Job;

import java.util.List;

public interface IJobService {
    List<Job> findAll();
    Job findJobById(Long id);
    Job createJob(Job job);
    Boolean deleteJobById(Long id);

    Job updateJob(Long id, Job job);


}
