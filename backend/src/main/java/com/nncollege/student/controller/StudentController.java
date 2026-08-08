package com.nncollege.student.controller;

import com.nncollege.student.dto.*;
import com.nncollege.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/students")
public class StudentController {
 private final StudentService service;
 public StudentController(StudentService service){this.service=service;}
 @PostMapping public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentCreateRequest request){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));}
 @GetMapping("/{id}") public StudentResponse get(@PathVariable Long id){return service.getById(id);}
 @GetMapping("/admission/{admissionNo}") public StudentResponse getByAdmission(@PathVariable String admissionNo){return service.getByAdmissionNo(admissionNo);}
 @GetMapping public Page<StudentResponse> search(@RequestParam(required=false) String department,@RequestParam(required=false) String query,@PageableDefault(size=20,sort="id",direction=Sort.Direction.DESC) Pageable pageable){return service.search(department,query,pageable);}
 @PutMapping("/{id}") public StudentResponse update(@PathVariable Long id,@Valid @RequestBody StudentCreateRequest request){return service.update(id,request);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
}