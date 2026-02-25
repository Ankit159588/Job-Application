package com.example.demo.DTO.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record JobApplicationResponseDTO(Long id,
                                        Long userId,
                                        String userName,
                                        String companyName,
                                        String jobTitle,
                                        String jobLink,
                                        String status,
                                        LocalDate appliedDate) {
}
