package com.example.demo.controller;

import com.example.demo.DTO.request.CreateJobApplicationRequestDTO;
import com.example.demo.DTO.request.UpdateJobApplicationRequestDTO;
import com.example.demo.DTO.response.JobApplicationResponseDTO;
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
    public JobApplicationResponseDTO create(
            @PathVariable Long userId,
            @RequestBody CreateJobApplicationRequestDTO dto
    ) {
        return jobApplicationService.createByDTO(userId, dto);
    }

    // read by id
    @GetMapping("/{id}")
    public JobApplicationResponseDTO getById(@PathVariable Long id){
        return jobApplicationService.getJobApplicationById(id);
    }

    // read all
    @GetMapping
    public List<JobApplicationResponseDTO> getAll(){
        return jobApplicationService.getAllJobApplication();
    }

    // READ by user
    @GetMapping("/user/{userId}")
    public List<JobApplicationResponseDTO> getByUser(@PathVariable Long userId) {
        return jobApplicationService.getJobApplicationsByUser(userId);
    }

    @PutMapping("/{id}")
    public JobApplicationResponseDTO update(@PathVariable Long id, @RequestBody UpdateJobApplicationRequestDTO jobApplication){
        return jobApplicationService.updateJobApplication(id, jobApplication);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jobApplicationService.deleteJobApplication(id);
    }

}
