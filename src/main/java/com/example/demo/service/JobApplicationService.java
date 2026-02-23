package com.example.demo.service;

import com.example.demo.Repository.JobApplicationRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.JobApplication;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;

    JobApplicationService(JobApplicationRepository jobApplicationRepository, UserRepository userRepository){
        this.jobApplicationRepository = jobApplicationRepository;
        this.userRepository = userRepository;
    }

    // create job application
    public JobApplication createJobApplication(Long userId, JobApplication jobApplication){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        jobApplication.setUser(user);
        return jobApplicationRepository.save(jobApplication);
    }

    public List<JobApplication> getAllJobApplication(){
        return jobApplicationRepository.findAll();
    }

    public JobApplication getJobApplicationById(Long id){
        return jobApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Application Not Found"));
    }

    public List<JobApplication> getJobApplicationsByUser(Long userId) {
        return jobApplicationRepository.findByUser_Id(userId);
    }

    public JobApplication updateJobApplication(Long id, JobApplication jobApplication){
        JobApplication existing = getJobApplicationById(id);
        existing.setCompanyName(jobApplication.getCompanyName());
        existing.setStatus(jobApplication.getStatus());
        return jobApplicationRepository.save(existing);
    }

    public void deleteJobApplication(Long id){
        jobApplicationRepository.deleteById(id);
    }

}
