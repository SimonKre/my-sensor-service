package com.skrezelok.mysensorservice.entity;

import javax.persistence.*;

@Entity
@Table (name = "alert")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private boolean overThresholdAlert;
    private float threshold;
    private boolean notifiedAlready;
    private boolean enabled;
    @ManyToOne //(cascade = CascadeType.ALL)
    private Sensor sensor;

    @ManyToOne
    private SensorDataType sensorDataType;

    public Alert() {
        this.enabled = true;
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

    public boolean getOverThresholdAlert() {
        return overThresholdAlert;
    }

    public void setOverThresholdAlert(boolean overThresholdAlert) {
        this.overThresholdAlert = overThresholdAlert;
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public boolean getNotifiedAlready() {
        return notifiedAlready;
    }

    public void setNotifiedAlready(boolean notifiedAlready) {
        this.notifiedAlready = notifiedAlready;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alert)) return false;
        Alert alert = (Alert) o;
        return id == alert.id;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", notifiedAlready=" + notifiedAlready +
                ", sensor=" + sensor +
                '}';
    }
}
