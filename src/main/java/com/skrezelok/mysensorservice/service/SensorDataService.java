package com.skrezelok.mysensorservice.service;

import com.skrezelok.mysensorservice.entity.*;
import com.skrezelok.mysensorservice.model.GenericSensor;
import com.skrezelok.mysensorservice.repository.AlertRepository;
import com.skrezelok.mysensorservice.repository.SensorDataRepository;
import com.skrezelok.mysensorservice.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorDataService {

    @Autowired
    private SensorRepository sensR;
    @Autowired
    private SensorDataRepository sDataR;
    @Autowired
    private AlertService alertService;
    @Autowired
    AlertRepository alertR;

    public void processSensorData(GenericSensor genericSensor) {

        Sensor sensor = sensR.findOneBySerialNumberEquals(genericSensor.getSerialNumber());
        SensorType sensType = sensor.getSensorType();

        for (SensorDataType sensDataType : sensType.getSensorDataTypes()) {

            float value = genericSensor.getData().get(sensDataType.getType());
            SensorData data = new SensorData(sensor, sensDataType, value);

            alertService.checkAlertsAndNotify(data);
            sDataR.save(data);
        }
    }
}
