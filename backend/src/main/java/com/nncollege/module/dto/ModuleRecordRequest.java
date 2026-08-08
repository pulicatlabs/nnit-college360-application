package com.nncollege.module.dto;

import jakarta.validation.constraints.*;
public record ModuleRecordRequest(@Size(max=50) String referenceNo,@NotBlank @Size(max=200) String name,@Size(max=30) String status,@Size(max=5000) String details,@Size(max=120) String owner,@DecimalMin("0") Double amount,@DecimalMin("0") @DecimalMax("100") Double percentage) {}