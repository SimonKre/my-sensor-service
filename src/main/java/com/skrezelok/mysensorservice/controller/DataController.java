package com.skrezelok.mysensorservice.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skrezelok.mysensorservice.entity.Sensor;
import com.skrezelok.mysensorservice.entity.security.User;
import com.skrezelok.mysensorservice.model.GoogleChartJson;
import com.skrezelok.mysensorservice.repository.SensorDataRepository;
import com.skrezelok.mysensorservice.repository.SensorRepository;
import com.skrezelok.mysensorservice.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private SensorDataRepository sdr;
    @Autowired
    private SensorRepository sr;
    @Autowired
    private UserRepository ur;
    @Autowired
    private GoogleChartJson googleChartJson;

    @GetMapping("")
    public String getData(Model model, Authentication auth) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        User user = ur.findByUsername(auth.getName());
        List<Sensor> sensors = sr.findAllWithAlertsByUserDetails(user.getUserDetails());

        Sensor sensor = sensors.stream()
                .filter(s -> s.getId() == 1)
                .collect(Collectors.toList())
                .get(0);

        googleChartJson.initilize(sensor.getSensorType());
        String jsonData = googleChartJson.getGoogleChartJsonFromSensorData(sensor.getSensorType(), sdr.findAllBySensorOrderByIdAsc(sensor));

        model.addAttribute("jsonData", jsonData);
        System.out.println(jsonData);
        return "/charts/chart";
    }

}
