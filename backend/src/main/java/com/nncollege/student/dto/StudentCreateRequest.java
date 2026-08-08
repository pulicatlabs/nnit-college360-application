package com.nncollege.student.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record StudentCreateRequest(@NotBlank @Size(max=30) String admissionNo,@NotBlank @Size(max=80) String firstName,@Size(max=80) String lastName,@Size(max=20) String gender,@Email String email,@Pattern(regexp="^[0-9+ -]{10,20}$",message="Invalid mobile") String mobile,String department,String course,String academicYear,@Min(1) @Max(8) Integer currentSemester,@DecimalMin("0.0") @DecimalMax("10.0") Double cgpa,String address,String parentName,String parentMobile,LocalDate dateOfBirth) {}