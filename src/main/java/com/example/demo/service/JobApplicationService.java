package com.example.demo.service;

import com.example.demo.DTO.request.CreateJobApplicationRequestDTO;
import com.example.demo.DTO.request.UpdateJobApplicationRequestDTO;
import com.example.demo.DTO.response.JobApplicationResponseDTO;
import com.example.demo.Repository.JobApplicationRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.JobApplication;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
//    public JobApplication createJobApplication(Long userId, JobApplication jobApplication){
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User Not Found"));
//        jobApplication.setUser(user);
//        return jobApplicationRepository.save(jobApplication);
//    }
    public JobApplicationResponseDTO createByDTO(Long userId, CreateJobApplicationRequestDTO dto){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        JobApplication app = new JobApplication();
        app.setUser(user);
        app.setCompanyName(dto.companyName());
        app.setJobTitle(dto.jobTitle());
        app.setJobLink(dto.jobLink());
        app.setStatus("APPLIED");
        app.setAppliedDate(LocalDate.now());

        JobApplication saved = jobApplicationRepository.save(app);

        return toResponse(saved);
    }


    public List<JobApplicationResponseDTO> getAllJobApplication(){
        return jobApplicationRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public JobApplicationResponseDTO getJobApplicationById(Long id){
//        return jobApplicationRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Job Application Not Found"));
        JobApplication app = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job application not found"));
        return toResponse(app);
    }

    public List<JobApplicationResponseDTO> getJobApplicationsByUser(Long userId) {
//        return jobApplicationRepository.findByUser_Id(userId);
        return jobApplicationRepository.findByUser_Id(userId)
                .stream()
                .map(this :: toResponse)
                .toList();
    }

    // UPDATE BY APPLICATION ID
    public JobApplicationResponseDTO updateJobApplication(Long id, UpdateJobApplicationRequestDTO dto){
//        JobApplication existing = getJobApplicationById(id);
//        existing.setCompanyName(jobApplication.getCompanyName());
//        existing.setStatus(jobApplication.getStatus());
//        return jobApplicationRepository.save(existing);
        JobApplication existing = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job application not found"));
        existing.setCompanyName(dto.companyName());
        existing.setJobTitle(dto.jobTitle());
        existing.setJobLink(dto.jobLink());
        existing.setStatus(dto.status());
        existing.setAppliedDate(dto.appliedDate());

        return toResponse(jobApplicationRepository.save(existing));
    }

    public void deleteJobApplication(Long id){
        if(!jobApplicationRepository.existsById(id)){
            throw new RuntimeException("Job Application not found");
        }

        jobApplicationRepository.deleteById(id);
    }

    public JobApplicationResponseDTO toResponse(JobApplication app){
        return new JobApplicationResponseDTO (
                app.getId(),
                app.getUser().getId(),
                app.getUser().getName(),
                app.getCompanyName(),
                app.getJobTitle(),
                app.getJobLink(),
                app.getStatus(),
                app.getAppliedDate()
        );
    }

}
