package com.example.demo.Repository;

import com.example.demo.entity.ApplicationNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationNoteRepository extends JpaRepository<ApplicationNote, Long> {

    Page<ApplicationNote> findByJobApplication_User_Id(Long userId, Pageable pageable);

    // ordered (newest first)
    List<ApplicationNote> findByJobApplication_IdOrderByCreatedAtDesc(Long jobApplicationId);

    // ✅ paged notes for a job application (optional but useful)
    Page<ApplicationNote> findByJobApplication_Id(Long jobApplicationId, Pageable pageable);
}