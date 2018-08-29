package com.skrezelok.mysensorservice.converter;

import com.skrezelok.mysensorservice.entity.SensorDataType;
import com.skrezelok.mysensorservice.repository.SensorDataTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;


public class SensorDataTypeConverter implements Converter<String, SensorDataType> {
    @Autowired
    SensorDataTypeRepository sensorDataTypeR;

    @Override
    public SensorDataType convert(String id) {
        return this.sensorDataTypeR.findOne(Long.parseLong(id));
    }
}
