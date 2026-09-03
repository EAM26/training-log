package com.emcode.training_log.Training;

import org.springframework.stereotype.Service;

@Service
public class TrainingService {

    private final TrainingRepo trainingRepo;

    public TrainingService(TrainingRepo trainingRepo) {
        this.trainingRepo = trainingRepo;
    }

    public Long createTraining(Training training) {
        return trainingRepo.save(training).getId();
    }
}
