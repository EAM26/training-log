package com.emcode.training_log.training;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String csvPath;
    private LocalDateTime createdAt;
    private LocalDateTime dateTimeOfTraining;
    private SportType type;
    private double speedAvg;
    private double distance;
    private int durationSeconds;



}
