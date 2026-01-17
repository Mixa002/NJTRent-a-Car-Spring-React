package com.rentacar.backend.dto;

import com.rentacar.backend.model.enums.StatusIznajmljivanja;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record IznajmljivanjeDTO(
        Integer iznajmljivanjeID,
        BigDecimal ukupanIznos,
        String opis,
        StatusIznajmljivanja status,
        LocalDateTime datumKreiranja,
        String klijentImePrezime, // npr. "Petar Petrović"
        List<StavkaIznajmljivanjaDTO> stavke
) {
}
