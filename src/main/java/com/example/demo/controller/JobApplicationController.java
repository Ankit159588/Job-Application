package com.example.demo.controller;

import com.example.demo.entity.JobApplication;
import com.example.demo.service.JobApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;
    JobApplicationController(JobApplicationService jobApplicationService){
        this.jobApplicationService = jobApplicationService;
    }

    // create - {userId, jobApplication}
    // CREATE
    @PostMapping("/user/{userId}")
    public JobApplication create(
            @PathVariable Long userId,
            @RequestBody JobApplication jobApplication
    ) {
        return jobApplicationService.createJobApplication(userId, jobApplication);
    }

    // read by id
    @GetMapping("/{id}")
    public JobApplication getById(@PathVariable Long id){
        return jobApplicationService.getJobApplicationById(id);
    }

    // read all
    @GetMapping
    public List<JobApplication> getAll(){
        return jobApplicationService.getAllJobApplication();
    }

    // READ by user
    @GetMapping("/user/{userId}")
    public List<JobApplication> getByUser(@PathVariable Long userId) {
        return jobApplicationService.getJobApplicationsByUser(userId);
    }

    @PutMapping("/{id}")
    public JobApplication update(@PathVariable Long id, @RequestBody JobApplication jobApplication){
        return jobApplicationService.updateJobApplication(id, jobApplication);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jobApplicationService.deleteJobApplication(id);
    }

}
