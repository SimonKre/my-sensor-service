package com.skrezelok.mysensorservice.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skrezelok.mysensorservice.entity.SensorData;
import com.skrezelok.mysensorservice.entity.SensorDataType;
import com.skrezelok.mysensorservice.entity.SensorType;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
public class GoogleChartJson {

    private ChartsDataTable chartsDataTable;

    public GoogleChartJson() {

    }

    public void initilize(SensorType sensorType) {
        chartsDataTable = new ChartsDataTable();
        chartsDataTable.addColumn("datetime", "datetime", "Data");

        for (SensorDataType type : sensorType.getSensorDataTypes()){
            this.chartsDataTable.addColumn("number", type.getType(), type.getDescription());
        }
    }

    public String getGoogleChartJsonFromSensorData(SensorType sensorType, List<SensorData> data) {

        ChartsDataTable.Row row = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy, MM, dd, HH, mm, ss");
        int dataColsNum = this.chartsDataTable.getCols().size() - 1;

        for (int i = 0; i < data.size(); i++ ) {

            if ((i + dataColsNum) % dataColsNum == 0) {
                row = this.chartsDataTable.createRow();
                //TODO make use of UTC time
                row.addCell("Date(" + data.get(i).getCreated()
                        //.atZone(ZoneId.of("+5"))
                        //.toLocalDateTime()
                        .format(formatter) + ")", "");
            }

            row.addCell(data.get(i).getValue(), String.format("%.2f", data.get(i).getValue()));
        }

        return getDataTableJson();
    }

    private String getDataTableJson () {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        return gson.toJson(this.chartsDataTable);
    }

}
