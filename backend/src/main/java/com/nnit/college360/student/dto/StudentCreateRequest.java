package com.nnit.college360.student.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record StudentCreateRequest(
    @NotBlank @Size(max=30) String admissionNo,
    @NotBlank @Size(max=100) String firstName,
    @Size(max=100) String lastName,
    @NotBlank String gender,
    LocalDate dateOfBirth,
    @Email @Size(max=120) String email,
    @Size(max=20) String mobile,
    @NotBlank @Size(max=100) String department,
    @NotBlank @Size(max=100) String course,
    @Size(max=50) String academicYear,
    @Min(1) @Max(8) Integer currentSemester,
    @DecimalMin("0.0") @DecimalMax("10.0") Double cgpa,
    @Size(max=500) String address,
    @Size(max=100) String parentName,
    @Size(max=20) String parentMobile
) {}
