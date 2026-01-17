package com.rentacar.backend.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateStavkaIznajmljivanjaDTO(
        @NotNull(message = "Automobil je obavezan")
        Integer automobilId,

        @NotNull(message = "Datum 'od' je obavezan")
        LocalDate datumOd,

        @NotNull(message = "Datum 'do' je obavezan")
        LocalDate datumDo,

        String napomena
) {
}
