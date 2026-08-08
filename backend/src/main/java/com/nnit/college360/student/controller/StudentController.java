package com.nnit.college360.student.controller;

import com.nnit.college360.student.dto.StudentCreateRequest;
import com.nnit.college360.student.dto.StudentResponse;
import com.nnit.college360.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService service;
    public StudentController(StudentService service){this.service=service;}

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse create(@Valid @RequestBody StudentCreateRequest request){return service.create(request);}

    @GetMapping("/{id}")
    public StudentResponse get(@PathVariable Long id){return service.getById(id);}

    @GetMapping
    public Page<StudentResponse> search(@RequestParam(required=false) String department,
                                        @RequestParam(defaultValue="0") int page,
                                        @RequestParam(defaultValue="20") int size){
        return service.search(department,PageRequest.of(page,size, Sort.by("id").descending()));
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id,@Valid @RequestBody StudentCreateRequest request){return service.update(id,request);}

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){service.delete(id);}
}
