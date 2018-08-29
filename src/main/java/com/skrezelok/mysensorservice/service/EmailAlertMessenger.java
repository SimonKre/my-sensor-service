package com.skrezelok.mysensorservice.service;

import com.skrezelok.mysensorservice.entity.Alert;
import com.skrezelok.mysensorservice.entity.SensorData;
import org.springframework.stereotype.Service;

@Service
public class EmailAlertMessenger implements AlertMessenger {

    @Override
    public boolean alertUser(Alert alert, SensorData data) {
        return false;
    }

    @Override
    public boolean reassureUser(Alert alert, SensorData data) {
        return false;
    }
}
