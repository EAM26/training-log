package com.emcode.training_log.util;

import com.emcode.training_log.training.Training;
import com.emcode.training_log.training.TrainingRequest;
import com.emcode.training_log.training.TrainingResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Mapper {

    public Training trainingRequestToEntity(TrainingRequest req) {
        Training training = new Training();
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
                training.getDate(),
                training.getTime(),
                training.getType(),
                training.getSpeedAvg(),
                training.getStride(),
                training.getDistance(),
                training.getDurationSeconds(),
                training.getHrAvg(),
                training.getTemperatureAvg(),
                training.getCadenceAvg()
        );
    }
}
