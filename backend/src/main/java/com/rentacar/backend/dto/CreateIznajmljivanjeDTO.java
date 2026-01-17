package com.rentacar.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateIznajmljivanjeDTO(
        String opis,

        @NotNull(message = "Klijent je obavezan")
        Integer klijentId,

        @NotEmpty(message = "Iznajmljivanje mora imati bar jednu stavku")
        List<CreateStavkaIznajmljivanjaDTO> stavke
) {
}
