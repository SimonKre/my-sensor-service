package com.skrezelok.mysensorservice.repository;

import com.skrezelok.mysensorservice.entity.SensorDataType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SensorDataTypeRepository extends JpaRepository<SensorDataType, Long> {

}
