package com.nncollege.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(ResourceNotFoundException.class)
 ResponseEntity<Map<String,Object>> notFound(ResourceNotFoundException e){return response(HttpStatus.NOT_FOUND,e.getMessage());}
 @ExceptionHandler(MethodArgumentNotValidException.class)
 ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException e){
   Map<String,String> errors=new LinkedHashMap<>(); e.getBindingResult().getFieldErrors().forEach(x->errors.put(x.getField(),x.getDefaultMessage()));
   Map<String,Object> body=new LinkedHashMap<>(); body.put("timestamp",Instant.now());body.put("status",400);body.put("message","Validation failed");body.put("errors",errors);return ResponseEntity.badRequest().body(body);
 }
 @ExceptionHandler(Exception.class)
 ResponseEntity<Map<String,Object>> generic(Exception e){return response(HttpStatus.INTERNAL_SERVER_ERROR,"Unexpected server error");}
 private ResponseEntity<Map<String,Object>> response(HttpStatus s,String m){return ResponseEntity.status(s).body(Map.of("timestamp",Instant.now(),"status",s.value(),"message",m));}
}