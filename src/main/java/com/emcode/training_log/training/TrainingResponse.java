package com.emcode.training_log.training;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record TrainingResponse(
        Long id,
        String name,
        String csvPath,
        LocalDateTime createdAt,
        LocalDate date,
        LocalTime time,
        TrainingType type,
        Double speedAvg,
        Double distance,
        Integer durationSeconds,
        Integer hrAvg
) {
}
