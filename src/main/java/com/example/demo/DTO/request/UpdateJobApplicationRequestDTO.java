package com.example.demo.DTO.request;

import java.time.LocalDate;

public record UpdateJobApplicationRequestDTO(String companyName,
                                             String jobTitle,
                                             String jobLink,
                                             String status,
                                             LocalDate appliedDate) {
}
