package com.skrezelok.mysensorservice.service;

import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import com.skrezelok.mysensorservice.entity.Alert;
import com.skrezelok.mysensorservice.entity.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SmsAlertMessenger implements AlertMessenger {

    @Autowired
    private NexmoSmsService nss;

    public SmsAlertMessenger() {
    }

    @Override
    public boolean reassureUser(Alert alert, SensorData data) {

        String clearAlertMessage = prepareClearAlertMessage(alert, data);
        String phone = data.getSensor().getUserDetails().getPhone();

        System.out.println("SMS na " + phone + ": " + clearAlertMessage + " " + (clearAlertMessage.length() + 29));

//        return true;
        return sendSms(phone, clearAlertMessage);
    }

    @Override
    public boolean alertUser(Alert alert, SensorData data) {

        String alertMessage = prepareAlertMessage(alert, data);
        String phone = data.getSensor().getUserDetails().getPhone();

        System.out.println("SMS na " + phone + ": " + alertMessage + " " + (alertMessage.length() + 29));
//        return true;
        return sendSms(phone, alertMessage);

    }

    private String prepareAlertMessage(Alert alert, SensorData data) {
        String alertMessage = "Uwaga! " + "Sensor (";

        alertMessage += data.getSensor().getName().length() > 14
                ? data.getSensor().getName().substring(0, 12) + ".."
                : data.getSensor().getName();

        alertMessage += ") ";
        alertMessage += alert.getOverThresholdAlert()
                ? data.getSensorDataType().getDataHighAlert()
                : data.getSensorDataType().getDataLowAlert();
        alertMessage += alert.getThreshold() + "! Wartość: " + String.format("%.2f", data.getValue()) + data.getSensorDataType().getUnit();

        return alertMessage;
    }

    private String prepareClearAlertMessage(Alert alert, SensorData data) {
        String alertMessage = "Uff! " + "Sensor (";

        alertMessage += data.getSensor().getName().length() > 14
                ? data.getSensor().getName().substring(0, 12) + ".."
                : data.getSensor().getName();

        alertMessage += ") " + data.getSensorDataType().getDescription();
        alertMessage += " już w normie! Wartość: " + String.format("%.2f", data.getValue()) + data.getSensorDataType().getUnit();

        return alertMessage;
    }

    private boolean sendSms(String phone, String message) {

        SmsSubmissionResult[] responses = new SmsSubmissionResult[0];
        try {
            responses = nss.getSmsClient().submitMessage(new TextMessage(
                    "MySensor",
                    phone,
                    message,
                    true
            ));
        } catch (
                IOException e) {
            e.printStackTrace();
            return false;
        } catch (
                NexmoClientException e) {
            e.printStackTrace();
            return false;
        }
        for (SmsSubmissionResult response : responses) {
            System.out.println(response);
        }
        return true;
    }
}
