package com.skrezelok.mysensorservice.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "sensor_type")
public class SensorType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    @ManyToMany (fetch = FetchType.EAGER)
    private List<SensorDataType> sensorDataTypes = new ArrayList<>();

    public SensorType() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SensorDataType> getSensorDataTypes() {
        return sensorDataTypes;
    }

    public void setSensorDataTypes(List<SensorDataType> sensorDataTypes) {
        this.sensorDataTypes = sensorDataTypes;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
