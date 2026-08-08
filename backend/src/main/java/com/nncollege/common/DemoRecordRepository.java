package com.nncollege.common;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DemoRecordRepository extends JpaRepository<DemoRecord,Long>{
 List<DemoRecord> findByModuleOrderByIdDesc(String module);
 long countByModule(String module);
}
