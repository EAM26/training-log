package com.emcode.training_log.util;

public record TrainingSample(
        String timeAsString,
        Integer hr,
        Integer cadence,
        Integer altitude,
        Double stride,
        Double distance,
        Double temperature,
        Double power

) {
}
