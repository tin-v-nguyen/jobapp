package com.tin.jobapp.job.service;

import com.tin.jobapp.job.model.Job;
import com.tin.jobapp.job.repository.JobRepository;
import com.tin.jobapp.job.service.interfaces.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobService implements IJobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job findJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Boolean deleteJobById(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Optional<Job> jobO = jobRepository.findById(id);
        if (jobO.isPresent()) {
            Job newJob = jobO.get();
            newJob.setDescription(job.getDescription());
            newJob.setLocation(job.getLocation());
            newJob.setTitle(job.getTitle());
            newJob.setMaxSalary(job.getMaxSalary());
            newJob.setMinSalary(job.getMinSalary());
            return jobRepository.save(newJob);
        }
        return null;
    }
}
