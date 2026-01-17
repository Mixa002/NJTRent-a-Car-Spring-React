package com.rentacar.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateRadnikEkspoDTO(
        @NotNull(message = "ID radnika je obavezan")
        Integer radnikId,

        @NotNull(message = "ID ekspoziture je obavezan")
        Integer ekspozituraId,

        @Positive(message = "Satnica mora biti veća od nule")
        Double satnica
) {
}
