package com.rentacar.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CreateEkspozituraDTO(
        @NotBlank(message = "Naziv ekspoziture je obavezan")
        String naziv,

        @NotBlank(message = "Adresa je obavezna")
        String adresa,

        @NotNull(message = "Morate izabrati grad")
        Integer gradId
) {
}
