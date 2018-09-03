package com.skrezelok.mysensorservice.service;

import com.skrezelok.mysensorservice.entity.Alert;
import com.skrezelok.mysensorservice.entity.SensorData;
import com.skrezelok.mysensorservice.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AlertService {

    @Autowired
    AlertMessenger alertMessenger;
    @Autowired
    AlertRepository alertR;

    public void checkAlertsAndNotify(SensorData data) {

        List<Alert> alerts = getEnabledAlerts(data);

        if (!alerts.isEmpty()) {
            for (Alert alert : alerts) {
                checkAlertAndNotify(data, alert);
            }
        }
    }

    private void checkAlertAndNotify(SensorData data, Alert alert) {
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

    private List<Alert> getEnabledAlerts(SensorData data) {

        List<Alert> alerts = data.getSensor().getAlerts().stream()
                .filter(alert -> alert.getEnabled())
                .filter(alert -> alert.getSensorDataType().equals(data.getSensorDataType()))
                .collect(Collectors.toList());

        return alerts;
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
