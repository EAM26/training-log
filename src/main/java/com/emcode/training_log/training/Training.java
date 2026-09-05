package com.emcode.training_log.training;

import com.emcode.training_log.util.TrainingSample;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
    private TrainingType type;
    private Double speedAvg;
    private Double distance;
    private Integer durationSeconds;
    private Integer hrAvg;

    @Transient
    private List<TrainingSample> trainingSamples;



}
