package com.skrezelok.mysensorservice.repository;

import com.skrezelok.mysensorservice.entity.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SensorTypeRepository extends JpaRepository<SensorType, Long> {

}
