package com.nncollege.module.repository;
import com.nncollege.module.entity.CollegeModuleRecord;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CollegeModuleRecordRepository extends JpaRepository<CollegeModuleRecord,Long> { Page<CollegeModuleRecord> findByModuleTypeIgnoreCase(String moduleType,Pageable pageable); }