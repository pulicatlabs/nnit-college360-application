package com.nnit.college360.student.dto;

import java.time.LocalDate;

public record StudentResponse(Long id, String admissionNo, String firstName, String lastName, String gender,
 LocalDate dateOfBirth, String email, String mobile, String department, String course, String academicYear,
 Integer currentSemester, Double cgpa, String status, String address, String parentName, String parentMobile) {}
