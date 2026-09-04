package com.emcode.training_log.training;

import com.emcode.training_log.exception.RecordNotFoundException;
import com.emcode.training_log.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public TrainingResponse getTraining(Long id) {
        return mapper.trainingEntityToResponse(trainingRepo.findById(id).orElseThrow(() ->
                new RecordNotFoundException("No training found with id: " + id)));
    }

    public List<TrainingResponse> getAllTrainings() {
        return trainingRepo.findAll()
                .stream()
                .map(mapper::trainingEntityToResponse)
                .toList();
    }

    public void deleteTraining(Long id) {
        trainingRepo.delete(trainingRepo.findById(id).orElseThrow(() ->
                new RecordNotFoundException("No training found with id: " + id)));
    }
}
