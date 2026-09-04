package com.emcode.training_log.training;

import java.time.LocalDateTime;

public record TrainingResponse(
        Long id,
        String name,
        String csvPath,
        LocalDateTime createdAt,
        LocalDateTime dateTimeOfTraining,
        SportType type,
        // todo replace primitive numerical types for wrappers after validation in Training Model is complete
        Double speedAvg,
        Double distance,
        Integer durationSeconds
) {
}
