package com.example.demo.service;

import com.example.demo.Repository.ApplicationNoteRepository;
import com.example.demo.Repository.JobApplicationRepository;
import com.example.demo.entity.ApplicationNote;
import com.example.demo.entity.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationNoteService {

    private final ApplicationNoteRepository noteRepository;
    private final JobApplicationRepository jobApplicationRepository;

    public ApplicationNoteService(
            ApplicationNoteRepository noteRepository,
            JobApplicationRepository jobApplicationRepository
    ) {
        this.noteRepository = noteRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public ApplicationNote createNote(Long jobApplicationId, ApplicationNote note) {
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() -> new RuntimeException("Job application not found"));

        note.setJobApplication(jobApplication);
        return noteRepository.save(note);
    }

    public Page<ApplicationNote> getNotesByUser(Long userId, Pageable pageable) {
        return noteRepository.findByJobApplication_User_Id(userId, pageable);
    }

    public ApplicationNote getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public List<ApplicationNote> getNotesByJobApplicationId(Long jobApplicationId) {
        return noteRepository.findByJobApplication_Id(jobApplicationId);
    }


    public ApplicationNote updateNote(Long id, ApplicationNote updated) {
        ApplicationNote existing = getNoteById(id);
        existing.setContent(updated.getContent());
        return noteRepository.save(existing);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

}
