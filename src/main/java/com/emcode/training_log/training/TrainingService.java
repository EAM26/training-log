package com.emcode.training_log.training;

import com.emcode.training_log.util.Mapper;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

    private final TrainingRepo trainingRepo;
    private final Mapper mapper;

    public TrainingService(TrainingRepo trainingRepo, Mapper mapper) {
        this.trainingRepo = trainingRepo;
        this.mapper = mapper;
    }

    public TrainingResponse createTraining(TrainingRequest request) {
        Training training = mapper.trainingRequestToEntity(request);
        return mapper.trainingEntityToResponse(trainingRepo.save(training));
    }
}
