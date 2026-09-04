package com.emcode.training_log.training;

import jakarta.validation.constraints.NotBlank;

public record TrainingRequest(

        @NotBlank
        String csvPath
        ) {
}
