package com.nncollege.student.repository;

import com.nncollege.student.entity.Student;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface StudentRepository extends JpaRepository<Student,Long> {
 Optional<Student> findByAdmissionNo(String admissionNo);
 boolean existsByAdmissionNo(String admissionNo);
 Page<Student> findByDepartmentIgnoreCase(String department, Pageable pageable);
 Page<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName,String lastName,Pageable pageable);
}