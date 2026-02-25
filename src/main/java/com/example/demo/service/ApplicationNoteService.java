package com.example.demo.service;

import com.example.demo.DTO.request.JobApplicationNoteRequestDTO;
import com.example.demo.DTO.response.JobApplicationNoteResponseDTO;
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

    // ✅ CREATE
    public JobApplicationNoteResponseDTO createNote(Long jobApplicationId, JobApplicationNoteRequestDTO req) {
        JobApplication jobApplication = jobApplicationRepository.findById(jobApplicationId)
                .orElseThrow(() -> new RuntimeException("Job application not found"));

        ApplicationNote note = new ApplicationNote();
        note.setJobApplication(jobApplication);
        note.setContent(req.content());

        ApplicationNote saved = noteRepository.save(note);
        return toResponse(saved);
    }

    //  GET ALL (paged)
    public Page<JobApplicationNoteResponseDTO> getAllNotes(Pageable pageable) {
        return noteRepository.findAll(pageable).map(this::toResponse);
    }

    // GET BY USER (paged)
    public Page<JobApplicationNoteResponseDTO> getNotesByUser(Long userId, Pageable pageable) {
        return noteRepository.findByJobApplication_User_Id(userId, pageable)
                .map(this::toResponse);
    }

    // GET BY ID
    public JobApplicationNoteResponseDTO getNoteById(Long id) {
        ApplicationNote note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        return toResponse(note);
    }

    // GET BY JOB APPLICATION (ordered list)
    public List<JobApplicationNoteResponseDTO> getNotesByJobApplicationId(Long jobApplicationId) {
        return noteRepository.findByJobApplication_IdOrderByCreatedAtDesc(jobApplicationId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // UPDATE
    public JobApplicationNoteResponseDTO updateNote(Long id, JobApplicationNoteRequestDTO req) {
        ApplicationNote existing = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        existing.setContent(req.content());
        ApplicationNote saved = noteRepository.save(existing);
        return toResponse(saved);
    }

    // DELETE
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    private JobApplicationNoteResponseDTO toResponse(ApplicationNote note) {
        return new JobApplicationNoteResponseDTO(
                note.getId(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }
}