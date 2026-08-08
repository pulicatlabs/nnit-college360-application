package com.nncollege.student.service;

import com.nncollege.exception.ResourceNotFoundException;
import com.nncollege.student.dto.*;
import com.nncollege.student.entity.Student;
import com.nncollege.student.repository.StudentRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class StudentServiceImpl implements StudentService {
 private final StudentRepository repository;
 public StudentServiceImpl(StudentRepository repository){this.repository=repository;}
 @Override public StudentResponse create(StudentCreateRequest r){if(repository.existsByAdmissionNo(r.admissionNo()))throw new IllegalArgumentException("Admission number already exists");return toResponse(repository.save(toEntity(r)));}
 @Override @Transactional(readOnly=true) public StudentResponse getById(Long id){return toResponse(repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found: "+id)));}
 @Override @Transactional(readOnly=true) public StudentResponse getByAdmissionNo(String no){return toResponse(repository.findByAdmissionNo(no).orElseThrow(()->new ResourceNotFoundException("Student not found: "+no)));}
 @Override @Transactional(readOnly=true) public Page<StudentResponse> search(String department,String query,Pageable pageable){Page<Student> page;if(department!=null&&!department.isBlank())page=repository.findByDepartmentIgnoreCase(department,pageable);else if(query!=null&&!query.isBlank())page=repository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query,query,pageable);else page=repository.findAll(pageable);return page.map(this::toResponse);}
 @Override public StudentResponse update(Long id,StudentCreateRequest r){Student s=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found: "+id));if(!s.getAdmissionNo().equals(r.admissionNo())&&repository.existsByAdmissionNo(r.admissionNo()))throw new IllegalArgumentException("Admission number already exists");copy(r,s);return toResponse(repository.save(s));}
 @Override public void delete(Long id){if(!repository.existsById(id))throw new ResourceNotFoundException("Student not found: "+id);repository.deleteById(id);}
 private Student toEntity(StudentCreateRequest r){Student s=new Student();copy(r,s);return s;}
 private void copy(StudentCreateRequest r,Student s){s.setAdmissionNo(r.admissionNo());s.setFirstName(r.firstName());s.setLastName(r.lastName());s.setGender(r.gender());s.setEmail(r.email());s.setMobile(r.mobile());s.setDepartment(r.department());s.setCourse(r.course());s.setAcademicYear(r.academicYear());s.setCurrentSemester(r.currentSemester());s.setCgpa(r.cgpa());s.setAddress(r.address());s.setParentName(r.parentName());s.setParentMobile(r.parentMobile());s.setDateOfBirth(r.dateOfBirth());}
 private StudentResponse toResponse(Student s){return new StudentResponse(s.getId(),s.getAdmissionNo(),s.getFirstName(),s.getLastName(),s.getGender(),s.getEmail(),s.getMobile(),s.getDepartment(),s.getCourse(),s.getAcademicYear(),s.getCurrentSemester(),s.getCgpa(),s.getAddress(),s.getParentName(),s.getParentMobile(),s.getDateOfBirth(),s.getActive());}
}