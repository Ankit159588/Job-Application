package com.example.demo.controller;

import com.example.demo.DTO.request.JobApplicationNoteRequestDTO;
import com.example.demo.DTO.response.JobApplicationNoteResponseDTO;
import com.example.demo.service.ApplicationNoteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application-notes")
public class ApplicationNoteController {

    private final ApplicationNoteService applicationNoteService;

    public ApplicationNoteController(ApplicationNoteService applicationNoteService) {
        this.applicationNoteService = applicationNoteService;
    }

    //  CREATE
    @PostMapping("/job-applications/{jobApplicationId}/notes")
    public JobApplicationNoteResponseDTO create(
            @PathVariable Long jobApplicationId,
            @RequestBody JobApplicationNoteRequestDTO req
    ) {
        return applicationNoteService.createNote(jobApplicationId, req);
    }

    // GET ALL (paged)
    // /api/application-notes?page=0&size=10&sort=createdAt,desc
    @GetMapping({"", "/"})
    public Page<JobApplicationNoteResponseDTO> getAll(Pageable pageable) {
        return applicationNoteService.getAllNotes(pageable);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public JobApplicationNoteResponseDTO getById(@PathVariable Long id) {
        return applicationNoteService.getNoteById(id);
    }

    //  GET notes for a job application (ordered)
    @GetMapping("/job-application/{jobApplicationId}")
    public List<JobApplicationNoteResponseDTO> getByJobApplication(
            @PathVariable Long jobApplicationId
    ) {
        return applicationNoteService.getNotesByJobApplicationId(jobApplicationId);
    }

    //  GET notes by user (paged)
    // /api/application-notes/user/3?page=0&size=10&sort=createdAt,desc
    @GetMapping("/user/{userId}")
    public Page<JobApplicationNoteResponseDTO> getByUser(
            @PathVariable Long userId,
            Pageable pageable
    ) {
        return applicationNoteService.getNotesByUser(userId, pageable);
    }

    //  UPDATE
    @PutMapping("/{id}")
    public JobApplicationNoteResponseDTO update(
            @PathVariable Long id,
            @RequestBody JobApplicationNoteRequestDTO req
    ) {
        return applicationNoteService.updateNote(id, req);
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        applicationNoteService.deleteNote(id);
    }
}