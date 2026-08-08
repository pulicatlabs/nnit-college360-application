package com.nncollege.module.dto;
import java.time.Instant;
public record ModuleRecordResponse(Long id,String moduleType,String referenceNo,String name,String status,String details,String owner,Double amount,Double percentage,Instant createdAt,Instant updatedAt) {}