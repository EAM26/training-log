package com.emcode.training_log.training;

import com.emcode.training_log.util.TrainingSample;
import com.emcode.training_log.exception.RecordNotFoundException;
import com.emcode.training_log.util.Mapper;
import com.emcode.training_log.util.PolarCsvReader;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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

        training.setTrainingSamples(readDataRows(csvData));
        setTrainingSummary(training);

        return mapper.trainingEntityToResponse(trainingRepo.save(training));
    }

    private void setTrainingSummary(Training training) {
        training.setDistance(training.getTrainingSamples().getLast().distance());
        training.setDurationSeconds(training.getTrainingSamples().size());

        training.setSpeedAvg(training.getDistance()/ training.getDurationSeconds() * 3.6);

        int totalHrCol = training.getTrainingSamples().stream().mapToInt(TrainingSample::hr).sum();
        training.setHrAvg(totalHrCol/ training.getDurationSeconds());
        double totalTemperatureCol = training.getTrainingSamples().stream().mapToDouble(TrainingSample::temperature).sum();
        training.setTemperatureAvg(totalTemperatureCol/ training.getDurationSeconds());
        int totalCadenceCol = training.getTrainingSamples().stream().mapToInt(TrainingSample::cadence).sum();
        training.setCadenceAvg(totalCadenceCol/ training.getDurationSeconds());

    }

    private List<TrainingSample> readDataRows(List<String[]> csvData) {
        return csvData
                .stream()
                .skip(3)
                .map(this::mapDataRowToTrainingSample)
                .toList();
    }

    private TrainingSample mapDataRowToTrainingSample(String[] row) {
        return new TrainingSample(
                row[1],
                Integer.parseInt(row[2]),
                Integer.parseInt(row[5]),
                Integer.parseInt(row[6]),
                parseNullableDouble(row[7]),
                Double.parseDouble(row[8]),
                Double.parseDouble(row[9]),
                Double.parseDouble(row[10])
        );
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
        String[] row1 = csvData.get(1);
        training.setName(row1[1] + ": " + row1[2] + "  " + row1[3]);
        training.setDate(LocalDate.parse(row1[2]));
        training.setTime(LocalTime.parse((row1[3])));
        training.setType(TrainingType.valueOf(row1[1]));
    }

    private Double parseNullableDouble(String value) {
        if(value == null || value.isBlank()) {
            return null;
        }
        return Double.parseDouble(value);
    }
}
