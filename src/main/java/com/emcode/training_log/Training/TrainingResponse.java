package com.emcode.training_log.Training;

import java.time.LocalDateTime;

public record TrainingResponse(
        Long id,
        String name,
        String csvPath,
        LocalDateTime createdAt,
        LocalDateTime dateTimeOfTraining,
        SportType type,
        double speedAvg,
        double distance,
        int durationSeconds
) {
}
