package com.skrezelok.mysensorservice.service;

import com.skrezelok.mysensorservice.entity.Alert;
import com.skrezelok.mysensorservice.entity.SensorData;

public interface AlertMessenger {
    boolean alertUser(Alert alert, SensorData data);

    boolean reassureUser(Alert alert, SensorData data);
}
