package com.skrezelok.mysensorservice.model;

import java.util.HashMap;
import java.util.Map;


public class GenericSensor {

    private String password = "blablabla";

    private long sensorType;

    private String serialNumber;

    Map<String, Float> data = new HashMap<>();

    public GenericSensor() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getSensorType() {
        return sensorType;
    }

    public void setSensorType(long sensorType) {
        this.sensorType = sensorType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Map<String, Float> getData() {
        return data;
    }

    public void setData(Map<String, Float> data) {
        this.data = data;
    }
}
