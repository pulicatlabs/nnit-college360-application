package com.nnit.college360.student.repository;

import com.nnit.college360.student.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByAdmissionNo(String admissionNo);
    Page<Student> findByDepartmentIgnoreCase(String department, Pageable pageable);
    boolean existsByAdmissionNo(String admissionNo);
    boolean existsByEmail(String email);
}
