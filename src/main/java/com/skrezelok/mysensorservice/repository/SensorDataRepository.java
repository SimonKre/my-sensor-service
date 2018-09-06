package com.skrezelok.mysensorservice.repository;

import com.skrezelok.mysensorservice.entity.Sensor;
import com.skrezelok.mysensorservice.entity.SensorData;
import com.skrezelok.mysensorservice.entity.SensorDataType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    SensorData findFirstBySensorAndSensorDataTypeOrderByCreatedDesc(Sensor sensor, SensorDataType dataType);

    List<SensorData> findAllBySensorAndSensorDataTypeOrderByCreatedDesc(long sensorId, long dataTypeId);
    List<SensorData> findAllBySensorOrderByIdAsc(Sensor sensor);
    List<SensorData> findAllBySensorAndCreatedGreaterThanOrderByIdAsc(Sensor sensor, LocalDateTime localDateTime);

    @Modifying
    @Query("DELETE FROM SensorData sd WHERE sd.sensor.id = :sensorId")
    void deleteAllBySensorId(@Param("sensorId") Long sensorId);
}

