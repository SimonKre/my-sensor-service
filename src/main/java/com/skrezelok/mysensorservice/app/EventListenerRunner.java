package com.skrezelok.mysensorservice.app;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
public class EventListenerRunner {
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        setDefaultTimezone();
    }

    public void setDefaultTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Paris"));
        System.out.println("Setting timezone to Europe/Paris");
    }
}