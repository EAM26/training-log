package com.emcode.training_log.Training;

import jakarta.validation.constraints.NotBlank;
public record TrainingRequest(

        String name,
        String csvPath
        ) {
}
