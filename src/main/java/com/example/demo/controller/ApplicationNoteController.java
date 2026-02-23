package com.example.demo.controller;

import com.example.demo.entity.ApplicationNote;
import com.example.demo.service.ApplicationNoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application-notes")
public class ApplicationNoteController {
    private final ApplicationNoteService applicationNoteService;

    public ApplicationNoteController(ApplicationNoteService applicationNoteService) {
        this.applicationNoteService = applicationNoteService;
    }

    // CREATE
    @PostMapping("/job-applications/{jobApplicationId}/notes")
    public ApplicationNote create(
            @PathVariable Long jobApplicationId,
            @RequestBody ApplicationNote note
    ) {
        return applicationNoteService.createNote(jobApplicationId, note);
    }

    // READ by id
    @GetMapping("/{id}")
    public ApplicationNote getById(@PathVariable Long id) {
        return applicationNoteService.getNoteById(id);
    }

    // READ all notes for a job application
    @GetMapping("/job-application/{jobApplicationId}")
    public List<ApplicationNote> getByJobApplication(
            @PathVariable Long jobApplicationId
    ) {
        return applicationNoteService.getNotesByJobApplicationId(jobApplicationId);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ApplicationNote update(
            @PathVariable Long id,
            @RequestBody ApplicationNote note
    ) {
        return applicationNoteService.updateNote(id, note);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        applicationNoteService.deleteNote(id);
    }

}
