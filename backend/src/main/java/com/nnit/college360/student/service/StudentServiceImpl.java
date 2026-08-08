package com.nnit.college360.student.service;

import com.nnit.college360.exception.ResourceNotFoundException;
import com.nnit.college360.student.dto.StudentCreateRequest;
import com.nnit.college360.student.dto.StudentResponse;
import com.nnit.college360.student.entity.Student;
import com.nnit.college360.student.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
    public StudentServiceImpl(StudentRepository repository){this.repository=repository;}

    @Override public StudentResponse create(StudentCreateRequest r){
        if(repository.existsByAdmissionNo(r.admissionNo())) throw new IllegalArgumentException("Admission number already exists");
        Student s=map(new Student(),r); return toResponse(repository.save(s));
    }
    @Override @Transactional(readOnly=true) public StudentResponse getById(Long id){return toResponse(repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found: "+id)));}
    @Override @Transactional(readOnly=true) public Page<StudentResponse> search(String department, Pageable pageable){
        Page<Student> page=(department==null||department.isBlank())?repository.findAll(pageable):repository.findByDepartmentIgnoreCase(department,pageable);
        return page.map(this::toResponse);
    }
    @Override public StudentResponse update(Long id, StudentCreateRequest r){
        Student s=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found: "+id));
        if(!s.getAdmissionNo().equals(r.admissionNo()) && repository.existsByAdmissionNo(r.admissionNo())) throw new IllegalArgumentException("Admission number already exists");
        return toResponse(repository.save(map(s,r)));
    }
    @Override public void delete(Long id){if(!repository.existsById(id))throw new ResourceNotFoundException("Student not found: "+id);repository.deleteById(id);}

    private Student map(Student s, StudentCreateRequest r){
        s.setAdmissionNo(r.admissionNo());s.setFirstName(r.firstName());s.setLastName(r.lastName());s.setGender(r.gender());s.setDateOfBirth(r.dateOfBirth());
        s.setEmail(r.email());s.setMobile(r.mobile());s.setDepartment(r.department());s.setCourse(r.course());s.setAcademicYear(r.academicYear());
        s.setCurrentSemester(r.currentSemester());s.setCgpa(r.cgpa());s.setAddress(r.address());s.setParentName(r.parentName());s.setParentMobile(r.parentMobile());return s;
    }
    private StudentResponse toResponse(Student s){return new StudentResponse(s.getId(),s.getAdmissionNo(),s.getFirstName(),s.getLastName(),s.getGender(),s.getDateOfBirth(),s.getEmail(),s.getMobile(),s.getDepartment(),s.getCourse(),s.getAcademicYear(),s.getCurrentSemester(),s.getCgpa(),s.getStatus(),s.getAddress(),s.getParentName(),s.getParentMobile());}
}
