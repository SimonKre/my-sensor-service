package com.skrezelok.mysensorservice.controller;


import com.skrezelok.mysensorservice.entity.Alert;
import com.skrezelok.mysensorservice.entity.Sensor;
import com.skrezelok.mysensorservice.entity.security.User;
import com.skrezelok.mysensorservice.model.GoogleChartJson;
import com.skrezelok.mysensorservice.repository.AlertRepository;
import com.skrezelok.mysensorservice.repository.SensorDataRepository;
import com.skrezelok.mysensorservice.repository.SensorRepository;
import com.skrezelok.mysensorservice.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@Transactional
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private UserRepository ur;
    @Autowired
    private SensorRepository sr;
    @Autowired
    private AlertRepository ar;
    @Autowired
    private GoogleChartJson googleChartJson;
    @Autowired
    private SensorDataRepository sdr;

    @GetMapping("{sensorId}/alerts")
    public String mySensorsAlerts(Authentication auth, Model model, @PathVariable long sensorId) {

        User user = ur.findByUsername(auth.getName());
        List<Sensor> sensors = sr.findAllWithAlertsByUserDetails(user.getUserDetails());

        Sensor sensor = sensors.stream()
                .filter(s -> s.getId() == sensorId)
                .collect(Collectors.toList())
                .get(0);

        if(sensor == null) {
            return "redirect:/";
        }

        model.addAttribute("sensor", sensor);
        model.addAttribute("alert", new Alert());

        return "alerts/list";
    }

    @GetMapping("{sensorId}/chart")
    public String mySensorsChart(Authentication auth, Model model, @PathVariable long sensorId) {

        User user = ur.findByUsername(auth.getName());
        List<Sensor> sensors = sr.findAllWithAlertsByUserDetails(user.getUserDetails());

        Sensor sensor = sensors.stream()
                .filter(s -> s.getId() == sensorId)
                .collect(Collectors.toList())
                .get(0);

        if(sensor == null) {
            return "redirect:/";
        }

        if (sensor != null) {
            googleChartJson.initilize(sensor.getSensorType());
            String jsonData = googleChartJson.getGoogleChartJsonFromSensorData(sensor.getSensorType(),
                    sdr.findAllBySensorAndCreatedGreaterThanOrderByIdAsc(sensor, LocalDateTime.now().minusMinutes(360)));
            model.addAttribute("jsonData", jsonData);
            model.addAttribute("sensor", sensor);
        }

        return "charts/chart";
    }

    @PostMapping("{sensorId}/alerts")
    public String saveAlert(Authentication auth, Model model, @ModelAttribute Alert alert,
                            @PathVariable long sensorId) {

        User user = ur.findByUsername(auth.getName());
        List<Sensor> sensors = sr.findAllWithAlertsByUserDetails(user.getUserDetails());

        Sensor sensor = sensors.stream()
                .filter(s -> s.getId() == alert.getSensor().getId())
                .collect(Collectors.toList())
                .get(0);

        if(sensor == null) {
            return "redirect:/";
        }

        if (sensor != null) {
            ar.save(alert);
        }

        return "redirect:alerts";
    }

    @GetMapping("{sensorId}/{alertId}")
    public String mySensorsAlerts(Authentication auth, Model model, @PathVariable long sensorId, @PathVariable long alertId) {

        User user = ur.findByUsername(auth.getName());
        List<Sensor> sensors = sr.findAllWithAlertsByUserDetails(user.getUserDetails());
        Alert alert = ar.findOne(alertId);

        Sensor sensor = sensors.stream()
                .filter(s -> s.getId() == sensorId)
                .collect(Collectors.toList())
                .get(0);

        if(sensor == null) {
            return "redirect:/";
        }

        if (sensor.getAlerts().contains(alert)) {
            model.addAttribute("alert", alert);
        } else {
            model.addAttribute("alert", null);
        }

        model.addAttribute("sensor", sensor);


        return "alerts/edit";
    }

    @PostMapping("{sensorId}/{alertId}")
    public String editAlert(Authentication auth, Model model, @PathVariable long sensorId,
                            @PathVariable long alertId, @ModelAttribute Alert alert) {

        User user = ur.findByUsername(auth.getName());
        List<Sensor> sensors = sr.findAllWithAlertsByUserDetails(user.getUserDetails());

        Sensor sensor = sensors.stream()
                .filter(s -> s.getId() == sensorId)
                .collect(Collectors.toList())
                .get(0);

        if(sensor == null) {
            return "redirect:/";
        }

        if (sensor.getAlerts().contains(alert)) {
            System.out.println("zapisuję alert");
            ar.save(alert);
        } else {
            System.out.println("nie zawiera alertu");
        }


        return "redirect:alerts";
    }

    @GetMapping("{sensorId}/{alertId}/delete")
    public String deleteAlert(Authentication auth, Model model, @PathVariable long sensorId, @PathVariable long alertId) {

        User user = ur.findByUsername(auth.getName());
        List<Sensor> sensors = sr.findAllWithAlertsByUserDetails(user.getUserDetails());
        Alert alert = ar.findOne(alertId);

        Sensor sensor = sensors.stream()
                .filter(s -> s.getId() == sensorId)
                .collect(Collectors.toList())
                .get(0);

        if(sensor == null) {
            return "redirect:/";
        }

        if (sensor.getAlerts().contains(alert)) {
            ar.delete(alert);
            System.out.println("TRYING TO DELETE ALERT");
            System.out.println(alert);
        }

        return "redirect:/sensor/" + sensorId + "/alerts";
    }

    @GetMapping("{sensorId}/delete")
    public String deleteSensor(Authentication auth, Model model, @PathVariable long sensorId) {

        User user = ur.findByUsername(auth.getName());
        List<Sensor> sensors = sr.findAllWithAlertsByUserDetails(user.getUserDetails());

        Sensor sensor = sensors.stream()
                .filter(s -> s.getId() == sensorId)
                .collect(Collectors.toList())
                .get(0);

        if (sensor != null) {
            sr.delete(sensor);
        }

        return "redirect:/";
    }

}
