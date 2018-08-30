package com.skrezelok.mysensorservice.controller;


import com.skrezelok.mysensorservice.entity.Sensor;
import com.skrezelok.mysensorservice.entity.SensorType;
import com.skrezelok.mysensorservice.entity.UserDetails;
import com.skrezelok.mysensorservice.entity.security.User;
import com.skrezelok.mysensorservice.repository.AlertRepository;
import com.skrezelok.mysensorservice.repository.SensorRepository;
import com.skrezelok.mysensorservice.repository.SensorTypeRepository;
import com.skrezelok.mysensorservice.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@Controller
@Transactional
public class HomePageController {

    @Autowired
    UserRepository ur;
    @Autowired
    SensorRepository sr;
    @Autowired
    AlertRepository ar;
    @Autowired
    SensorTypeRepository str;

    @GetMapping("")
    public String mySensors(Authentication auth, Model model) {

        User user = ur.findByUsername(auth.getName());
        List<Sensor> sensors = sr.findAllWithAlertsByUserDetails(user.getUserDetails());

        model.addAttribute("sensors", sensors);
        model.addAttribute("sensor", new Sensor());

        return "sensors/list";
    }

    @PostMapping("")
    public String addSensor(Authentication auth, Model model, @Valid Sensor sensor, BindingResult result) {

        if (result.hasErrors()) {

            User user = ur.findByUsername(auth.getName());
            List<Sensor> sensors = sr.findAllWithAlertsByUserDetails(user.getUserDetails());
            model.addAttribute("sensors", sensors);
            return "sensors/list";
        }

        if (sr.findOneBySerialNumberEquals(sensor.getSerialNumber()) == null) {

            User user = ur.findByUsername(auth.getName());
            UserDetails userDetails = user.getUserDetails();
            sensor.setUserDetails(userDetails);
            sr.save(sensor);
        }

        return ("redirect:/");
    }

    @ModelAttribute("sensorTypes")
    public List<SensorType> sensorTypes() {
        return str.findAll();
    }

}
