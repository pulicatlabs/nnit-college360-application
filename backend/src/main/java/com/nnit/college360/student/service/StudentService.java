package com.nnit.college360.student.service;

import com.nnit.college360.student.dto.StudentCreateRequest;
import com.nnit.college360.student.dto.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    StudentResponse create(StudentCreateRequest request);
    StudentResponse getById(Long id);
    Page<StudentResponse> search(String department, Pageable pageable);
    StudentResponse update(Long id, StudentCreateRequest request);
    void delete(Long id);
}
