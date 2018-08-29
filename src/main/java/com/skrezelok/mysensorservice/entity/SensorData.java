package com.skrezelok.mysensorservice.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

@Entity
@Table(name = "sensor_data")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Sensor sensor;

    @ManyToOne
    private SensorDataType sensorDataType;

    private float value;

    //TODO make use of UTC time instead of local
    //TODO check why columnDefinition default not working on GAE
    //@CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created;

    public SensorData() {
    }

    public SensorData(Sensor sensor, SensorDataType sensorDataType, float value) {
        this.sensor = sensor;
        this.sensorDataType = sensorDataType;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public SensorDataType getSensorDataType() {
        return sensorDataType;
    }

    public void setSensorDataType(SensorDataType sensorDataType) {
        this.sensorDataType = sensorDataType;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public LocalDateTime getCreated() {
        //return created.atZone(ZoneId.of("Europe/Warsaw")).toLocalDateTime();
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
