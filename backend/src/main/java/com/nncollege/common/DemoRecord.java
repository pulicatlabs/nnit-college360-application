package com.nncollege.common;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity @Table(name="demo_records")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DemoRecord {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String module;
 @Column(nullable=false) private String name;
 @Column(length=5000) private String details;
 @Column(nullable=false) private String status;
 private LocalDateTime createdAt;
 private LocalDateTime updatedAt;
 @PrePersist void pre(){createdAt=LocalDateTime.now();updatedAt=createdAt;}
 @PreUpdate void upd(){updatedAt=LocalDateTime.now();}
}
