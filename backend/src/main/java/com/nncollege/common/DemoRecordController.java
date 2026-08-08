package com.nncollege.common;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class DemoRecordController {
 private final DemoRecordRepository repo;
 public DemoRecordController(DemoRecordRepository repo){this.repo=repo;}
 @GetMapping("/modules/{module}") public List<DemoRecord> list(@PathVariable String module){return repo.findByModuleOrderByIdDesc(module);}
 @PostMapping("/modules/{module}") public DemoRecord create(@PathVariable String module,@RequestBody DemoRecord r){r.setId(null);r.setModule(module);if(r.getStatus()==null)r.setStatus("ACTIVE");return repo.save(r);}
 @PutMapping("/records/{id}") public DemoRecord update(@PathVariable Long id,@RequestBody DemoRecord r){DemoRecord old=repo.findById(id).orElseThrow();old.setName(r.getName());old.setDetails(r.getDetails());old.setStatus(r.getStatus());return repo.save(old);}
 @DeleteMapping("/records/{id}") public void delete(@PathVariable Long id){repo.deleteById(id);}
 @GetMapping("/dashboard") public Map<String,Object> dashboard(){return Map.of("students",repo.countByModule("STUDENT"),"faculty",repo.countByModule("FACULTY"),"attendance",86,"placement",78,"libraryIssued",142,"feesDue",18,"tasksCompleted",72);}
}
