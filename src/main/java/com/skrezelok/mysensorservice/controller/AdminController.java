package com.skrezelok.mysensorservice.controller;


import com.skrezelok.mysensorservice.entity.Sensor;
import com.skrezelok.mysensorservice.entity.SensorDataType;
import com.skrezelok.mysensorservice.entity.SensorType;
import com.skrezelok.mysensorservice.entity.UserDetails;
import com.skrezelok.mysensorservice.entity.security.User;
import com.skrezelok.mysensorservice.repository.AlertRepository;
import com.skrezelok.mysensorservice.repository.SensorDataTypeRepository;
import com.skrezelok.mysensorservice.repository.SensorRepository;
import com.skrezelok.mysensorservice.repository.SensorTypeRepository;
import com.skrezelok.mysensorservice.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@Controller
@Transactional
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserRepository ur;
    @Autowired
    SensorRepository sr;
    @Autowired
    AlertRepository ar;
    @Autowired
    SensorTypeRepository str;
    @Autowired
    SensorDataTypeRepository sensorDataTypeRepository;
    @Autowired
    SensorTypeRepository sensorTypeRepository;

    @GetMapping("sensor-types")
    public String sensorTypes(Authentication auth, Model model) {

        return "admin/sensor-types-list";
    }

    @PostMapping("sensor-types")
    public String addSensor(@Valid SensorType sensorType, BindingResult result) {

        if (result.hasErrors()) {
            return "admin/sensor-types-list";
        }

        sensorTypeRepository.save(sensorType);
        return ("redirect:sensor-types");
    }

    @GetMapping("sensor-types/{sensorTypeId}/delete")
    public String deleteType(@PathVariable long sensorTypeId) {

        sensorTypeRepository.delete(sensorTypeId);
        return ("redirect:/admin/sensor-types");
    }

    @GetMapping("sensor-data-types")
    public String sensorDataTypes(Authentication auth, Model model) {

        return "admin/sensor-data-types-list";
    }

    @PostMapping("sensor-data-types")
    public String sensorDataTypes(@Valid SensorDataType sensorDataType, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/sensor-data-types-list";
        }

        sensorDataTypeRepository.save(sensorDataType);
        return ("redirect:sensor-data-types");
    }

    @GetMapping("sensor-data-types/{sensorDataTypeId}/delete")
    public String deleteDataType(@PathVariable long sensorDataTypeId) {

        sensorDataTypeRepository.delete(sensorDataTypeId);
        return ("redirect:/admin/sensor-data-types");
    }

    @GetMapping("sensor-data-types/{sensorDataTypeId}/edit")
    public String editDataType(@PathVariable long sensorDataTypeId, Model model) {

        model.addAttribute("editedDataType", sensorDataTypeRepository.findOne(sensorDataTypeId));

        return ("/admin/sensor-data-type-edit");
    }

    @PostMapping("sensor-data-types/{sensorDataTypeId}/edit")
    public String saveEditedDataType(@Valid SensorDataType sensorDataType, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("editedDataType", sensorDataType);
            return "admin/sensor-data-type-edit";
        }

        sensorDataTypeRepository.save(sensorDataType);
        return ("redirect:/admin/sensor-data-types");
    }


    @ModelAttribute("sensorTypes")
    public List<SensorType> sensorTypes() {
        return str.findAll();
    }

    @ModelAttribute("sensorType")
    public SensorType sensorType() {
        return new SensorType();
    }

    @ModelAttribute("sensorDataTypes")
    public List<SensorDataType> sensorDataTypes() {
        return sensorDataTypeRepository.findAll();
    }

    @ModelAttribute("sensorDataType")
    public SensorDataType sensorDataType() {
        return new SensorDataType();
    }

}
