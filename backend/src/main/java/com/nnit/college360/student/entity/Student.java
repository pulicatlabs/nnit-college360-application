package com.nnit.college360.student.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="students", indexes={@Index(name="idx_student_admission_no", columnList="admission_no"), @Index(name="idx_student_department", columnList="department")})
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Student {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="admission_no", nullable=false, unique=true, length=30) private String admissionNo;
    @Column(nullable=false, length=100) private String firstName;
    @Column(length=100) private String lastName;
    @Column(nullable=false, length=20) private String gender;
    private LocalDate dateOfBirth;
    @Column(unique=true, length=120) private String email;
    @Column(length=20) private String mobile;
    @Column(length=100) private String department;
    @Column(length=100) private String course;
    @Column(length=50) private String academicYear;
    private Integer currentSemester;
    private Double cgpa;
    @Column(length=30) private String status;
    @Column(length=500) private String address;
    @Column(length=100) private String parentName;
    @Column(length=20) private String parentMobile;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist void prePersist(){createdAt=LocalDateTime.now();updatedAt=createdAt; if(status==null) status="ACTIVE";}
    @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
}
