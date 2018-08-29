package com.skrezelok.mysensorservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.skrezelok.mysensorservice.model.GenericSensor;
import com.skrezelok.mysensorservice.repository.AlertRepository;
import com.skrezelok.mysensorservice.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/sensor-logger")
public class SensorLoggerController {

    @Autowired
    SensorDataService ls;

    @Autowired
    AlertRepository ar;

    @GetMapping("")
    @ResponseBody
    public String receiveSensorGet(Authentication authentication) {
//        AuthMethod auth = new TokenAuthMethod("81916043", "3dJcQrypFPMflJcc");
//        NexmoClient client = new NexmoClient(auth);
//
//        SmsSubmissionResult[] responses = new SmsSubmissionResult[0];
//        try {
//            responses = client.getSmsClient().submitMessage(new TextMessage(
//                    "Szymonek CO",
//                    "48606410561",
//                    "Testowy sms z JAVY"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NexmoClientException e) {
//            e.printStackTrace();
//        }
//        for (SmsSubmissionResult response : responses) {
//            System.out.println(response);
//        }
//        Alert alert = new Alert();
//        alert.setIsOverThresholdAlert(true);
//        alert.setName("sdfsdf");
//        ar.save(alert);
//        System.out.println("Wyslano smsa");
        String message = " test";
//        message += ((UserDetails)authentication.getPrincipal()).getAuthorities().toString() + "<br>";
//        message += authentication.getName() + "<br>";
//        message += authentication.getDetails().toString() + "<br>";
//        message += authentication.getAuthorities() != null ? authentication.getAuthorities().toString() + "<br>" : "brak authorities <br>";


        return "HTTP/1.1 200 Ok\n";
    }


    @PostMapping("")
    @ResponseBody
    @Transactional
    public String receiveSensorJson(@RequestBody String jsonString) {
        ObjectMapper om = new ObjectMapper();
        GenericSensor sensor = new GenericSensor();
        String pass = sensor.getPassword();

        try {
            sensor = om.readValue(jsonString, GenericSensor.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (sensor.getPassword().equals(pass)) {
            System.out.println();
            System.out.println("Otrzymano posta od znanego sensora");
            System.out.println(sensor.getData());
            ls.processSensorData(sensor);
        } else {
            System.out.println("Not recognized sensor tried to log");
        }

        return "HTTP/1.1 200 Ok";
    }
}
