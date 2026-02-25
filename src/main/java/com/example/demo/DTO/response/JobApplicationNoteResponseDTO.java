package com.example.demo.DTO.response;

import java.time.LocalDateTime;

public record JobApplicationNoteResponseDTO(Long id,
                                            String content,
                                            LocalDateTime createdAt,
                                            LocalDateTime updatedAt) {
}
