package com.nncollege.module.controller;
import com.nncollege.module.dto.*;import com.nncollege.module.service.CollegeModuleService;import jakarta.validation.Valid;import org.springframework.data.domain.*;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/modules") public class CollegeModuleController {
 private final CollegeModuleService service; public CollegeModuleController(CollegeModuleService service){this.service=service;}
 @PostMapping("/{module}") ResponseEntity<ModuleRecordResponse> create(@PathVariable String module,@Valid @RequestBody ModuleRecordRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(module,r));}
 @GetMapping("/{module}/{id}") ModuleRecordResponse get(@PathVariable String module,@PathVariable Long id){return service.get(module,id);}
 @GetMapping("/{module}") Page<ModuleRecordResponse> list(@PathVariable String module,@PageableDefault(size=20,sort="id",direction=Sort.Direction.DESC) Pageable p){return service.list(module,p);}
 @PutMapping("/{module}/{id}") ModuleRecordResponse update(@PathVariable String module,@PathVariable Long id,@Valid @RequestBody ModuleRecordRequest r){return service.update(module,id,r);}
 @DeleteMapping("/{module}/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void delete(@PathVariable String module,@PathVariable Long id){service.delete(module,id);}
}