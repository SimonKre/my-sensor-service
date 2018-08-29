package com.skrezelok.mysensorservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "alert_type")
public class AlertType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
