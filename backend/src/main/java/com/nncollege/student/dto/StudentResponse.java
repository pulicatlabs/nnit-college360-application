package com.nncollege.student.dto;

import java.time.*;
public record StudentResponse(Long id,String admissionNo,String firstName,String lastName,String gender,String email,String mobile,String department,String course,String academicYear,Integer currentSemester,Double cgpa,String address,String parentName,String parentMobile,LocalDate dateOfBirth,Boolean active) {}