package com.emcode.training_log.training;

import com.emcode.training_log.TrainingLogApplication;
import com.emcode.training_log.exception.RecordNotFoundException;
import com.emcode.training_log.util.Mapper;
import com.emcode.training_log.util.PolarCsvReader;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

    private final TrainingRepo trainingRepo;
    private final Mapper mapper;
    private final PolarCsvReader reader;

    public TrainingService(TrainingRepo trainingRepo, Mapper mapper, PolarCsvReader reader) {
        this.trainingRepo = trainingRepo;
        this.mapper = mapper;
        this.reader = reader;
    }

    public TrainingResponse createTraining(TrainingRequest request) {
        Training training = mapper.trainingRequestToEntity(request);

        List<String[]> csvData = reader.readData(request.csvPath());
        setMetaData(training, csvData);
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



    private void setMetaData(Training training, List<String[]> csvData) {
//        String[] row0 = csvData.get(0);
        String[] row1 = csvData.get(1);
        training.setType(SportType.valueOf(row1[1]));


    }
}
