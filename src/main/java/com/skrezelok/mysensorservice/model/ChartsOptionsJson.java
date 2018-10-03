package com.skrezelok.mysensorservice.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skrezelok.mysensorservice.entity.SensorDataType;
import com.skrezelok.mysensorservice.entity.SensorType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChartsOptionsJson {
    private ChartsViewOptions options;

    public ChartsOptionsJson() {
    }

    private void generateOptions(SensorType sensorType) {
        this.options = new ChartsViewOptions();
        List<SensorDataType> dataTypes = sensorType.getSensorDataTypes();

        for (int i = 0; i < dataTypes.size(); i++) {
            this.options.addSeries(i, i);
            this.options.addAxe(i, dataTypes.get(i).getDescription(), i < 2 ? "out" : "in");
        }
    }

    public String getChartsOptionsJson (SensorType sensorType) {
        generateOptions(sensorType);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        return gson.toJson(this.options);
    }
}
