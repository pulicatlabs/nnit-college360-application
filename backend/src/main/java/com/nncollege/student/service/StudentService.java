package com.nncollege.student.service;

import com.nncollege.student.dto.*;
import org.springframework.data.domain.*;

public interface StudentService {
 StudentResponse create(StudentCreateRequest request);
 StudentResponse getById(Long id);
 StudentResponse getByAdmissionNo(String admissionNo);
 Page<StudentResponse> search(String department,String query,Pageable pageable);
 StudentResponse update(Long id,StudentCreateRequest request);
 void delete(Long id);
}