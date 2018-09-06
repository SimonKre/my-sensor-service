package com.skrezelok.mysensorservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedEntityGraph(name = "Sensor.alerts",
        attributeNodes = @NamedAttributeNode("alerts"))
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //TODO implement individual sensor passwords
    private String password = "blablabla";

    private String name;

    @Column(unique = true)
    @Size(min = 6, max = 20, message = "6 - 20 znaków!")
    @NotNull
    private String serialNumber;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private SensorType sensorType;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.REMOVE)
    private List<Alert> alerts = new ArrayList<>();

    @ManyToOne
    private UserDetails userDetails;

    private String location;


    public Sensor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
