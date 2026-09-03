package com.emcode.training_log.util;

import com.emcode.training_log.Training.Training;
import com.emcode.training_log.Training.TrainingRequest;
import com.emcode.training_log.Training.TrainingResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Mapper {

    public Training trainingRequestToEntity(TrainingRequest req) {
        Training training = new Training();
        training.setName(req.name());
        training.setCsvPath(req.csvPath());
        training.setCreatedAt(LocalDateTime.now());
        return training;
    }

    public TrainingResponse trainingEntityToResponse(Training training) {
        return new TrainingResponse(
                training.getId(),
                training.getName(),
                training.getCsvPath(),
                training.getCreatedAt(),
                training.getDateTimeOfTraining(),
                training.getType(),
                training.getSpeedAvg(),
                training.getDistance(),
                training.getDurationSeconds()
        );
    }
}
