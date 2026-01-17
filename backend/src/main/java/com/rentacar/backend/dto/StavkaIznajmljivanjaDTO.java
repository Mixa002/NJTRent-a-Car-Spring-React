package com.rentacar.backend.dto;

import java.time.LocalDate;

public record StavkaIznajmljivanjaDTO(
        Integer stavkaID,
        LocalDate datumOd,
        LocalDate datumDo,
        Double cenaStavke,
        String napomena,
        Integer automobilId,
        String automobilInfo // npr. "BMW 320d (BG-123-XY)"
) {
}
