package com.nncollege.student.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity @Table(name="students", indexes={@Index(name="idx_student_admission_no",columnList="admissionNo",unique=true),@Index(name="idx_student_department",columnList="department")})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Student {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=30,unique=true) private String admissionNo;
 @Column(nullable=false,length=80) private String firstName;
 @Column(length=80) private String lastName;
 @Column(length=20) private String gender;
 @Column(length=120) private String email;
 @Column(length=20) private String mobile;
 @Column(length=100) private String department;
 @Column(length=100) private String course;
 @Column(length=30) private String academicYear;
 private Integer currentSemester;
 private Double cgpa;
 @Column(length=200) private String address;
 @Column(length=100) private String parentName;
 @Column(length=20) private String parentMobile;
 private LocalDate dateOfBirth;
 private Boolean active;
 private Instant createdAt;
 private Instant updatedAt;
 @PrePersist void create(){createdAt=Instant.now();updatedAt=createdAt;if(active==null)active=true;}
 @PreUpdate void update(){updatedAt=Instant.now();}
}