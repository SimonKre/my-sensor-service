package com.skrezelok.mysensorservice.repository;

import com.skrezelok.mysensorservice.entity.Sensor;
import com.skrezelok.mysensorservice.entity.UserDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Sensor findOneBySerialNumberEquals(String serialNumber);

    @EntityGraph(value = "Sensor.alerts", type = EntityGraph.EntityGraphType.LOAD)
    List<Sensor> findAllWithAlertsByUserDetails(UserDetails userDetails);
    List<Sensor> findAllBySerialNumber(String serialNumber);
}

