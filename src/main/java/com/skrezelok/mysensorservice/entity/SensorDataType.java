package com.skrezelok.mysensorservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "sensor_data_type")
public class SensorDataType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String description;
    private String dataLowAlert;
    private String dataHighAlert;
    private double histeresis;
    private String unit;


    public SensorDataType() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataLowAlert() {
        return dataLowAlert;
    }

    public void setDataLowAlert(String dataLowAlert) {
        this.dataLowAlert = dataLowAlert;
    }

    public String getDataHighAlert() {
        return dataHighAlert;
    }

    public void setDataHighAlert(String dataHighAlert) {
        this.dataHighAlert = dataHighAlert;
    }

    public double getHisteresis() {
        return histeresis;
    }

    public void setHisteresis(double histeresis) {
        this.histeresis = histeresis;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
