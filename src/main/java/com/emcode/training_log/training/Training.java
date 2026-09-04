package com.emcode.training_log.training;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;
    @NotBlank
    private String csvPath;
    @NotNull
    private LocalDateTime createdAt;
    private LocalDate date;
    private LocalTime time;
    private SportType type;
    private Double speedAvg;
    private Double distance;
    private Integer durationSeconds;
    private Integer hrAvg;



}
