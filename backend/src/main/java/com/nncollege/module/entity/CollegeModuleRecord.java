package com.nncollege.module.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Table(name="college_module_records",indexes={@Index(name="idx_module_type",columnList="moduleType"),@Index(name="idx_module_reference",columnList="referenceNo")})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CollegeModuleRecord {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=40) private String moduleType;
 @Column(length=50) private String referenceNo;
 @Column(nullable=false,length=200) private String name;
 @Column(length=30) private String status;
 @Column(length=5000) private String details;
 @Column(length=120) private String owner;
 private Double amount;
 private Double percentage;
 private Instant createdAt;
 private Instant updatedAt;
 @PrePersist void pre(){createdAt=Instant.now();updatedAt=createdAt;}
 @PreUpdate void update(){updatedAt=Instant.now();}
}