package com.emcode.training_log.data_row;

public record TrainingSample(
        String timeAsString,
        Integer hr,
        Integer cadence,
        Integer altitude,
        Double distance,
        Double temperature,
        Double power
) {
}
