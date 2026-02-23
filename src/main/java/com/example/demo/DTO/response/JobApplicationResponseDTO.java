package com.example.demo.DTO.response;

import java.time.LocalDate;

public record JobApplicationResponseDTO(Long id,
                                        Long userId,
                                        String userName,
                                        String companyName,
                                        String jobTitle,
                                        String jobLink,
                                        String status,
                                        LocalDate appliedDate) {
}
