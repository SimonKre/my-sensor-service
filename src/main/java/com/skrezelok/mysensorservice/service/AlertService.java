package com.skrezelok.mysensorservice.service;

import com.skrezelok.mysensorservice.entity.Alert;
import com.skrezelok.mysensorservice.entity.SensorData;
import com.skrezelok.mysensorservice.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class AlertService {

    @Qualifier("smsAlertMessenger")
    @Autowired
    AlertMessenger alertMessenger;
    @Autowired
    AlertRepository alertR;

    public void checkAlertsAndNotify(SensorData data) {

        Alert alert = getAlert(data);

        if (alert != null) {

            boolean isAlertTriggered = isAlertTriggered(data, alert);

            if (isAlertTriggered && !alert.getNotifiedAlready()) {

                if (alertMessenger.alertUser(alert, data)) {
                    alert.setNotifiedAlready(true);
                    alertR.save(alert);
                }

            } else if (!isAlertTriggered && alert.getNotifiedAlready()) {

                if (alertMessenger.reassureUser(alert, data)) {
                    alert.setNotifiedAlready(false);
                    alertR.save(alert);
                }

            } else if (alert.getNotifiedAlready()){
                System.out.println("Już powiadomiono");
            } else {
                System.out.println("Wszystko ok i nic się nie zmieniło");
            }
        }
    }

    private Alert getAlert(SensorData data) {

        for (Alert alert : data.getSensor().getAlerts()) {

            if (alert.getSensorDataType().equals(data.getSensorDataType())) {
                if (alert.getEnabled()) return alert;
            }
        }
        System.out.println(data.getSensorDataType().getDescription() + " - brak alertu");
        return null;
    }

    private boolean isAlertTriggered(SensorData data, Alert alert) {

        boolean response = false;
        double histeresis = 0;

        if (alert.getNotifiedAlready()) histeresis -= alert.getSensorDataType().getHisteresis();
        else histeresis += alert.getSensorDataType().getHisteresis();

        if (alert.getOverThresholdAlert()) {

            if (data.getValue() > alert.getThreshold() + histeresis) {
                System.out.println("ALERT: PRZEKROCZONO - " + data.getSensorDataType().getDescription() + " " + data.getValue());
                response = true;
            } else {
                System.out.println(data.getSensorDataType().getDescription() + " jest OK - " + data.getValue());
                response = false;
            }
        } else {

            if (data.getValue() < alert.getThreshold() - histeresis) {
                System.out.println("ALERT: ZA MAŁO - " + data.getSensorDataType().getDescription() + " " + data.getValue());
                response = true;
            } else {
                System.out.println(data.getSensorDataType().getDescription() + " jest OK - " + data.getValue());
                response = false;
            }
        }
        return response;
    }
}
