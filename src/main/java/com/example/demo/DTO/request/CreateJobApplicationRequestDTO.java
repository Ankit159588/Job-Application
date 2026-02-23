package com.example.demo.DTO.request;

import java.time.LocalDate;

public record CreateJobApplicationRequestDTO(String companyName,
                                             String jobTitle,
                                             String jobLink,
                                             LocalDate appliedDate) {
}
