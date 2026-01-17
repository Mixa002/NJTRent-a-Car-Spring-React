package com.rentacar.backend.dto;

public record RadnikEkspoDTO(
        Integer radnikId,
        String radnikImePrezime, // npr. "Marko Marković"
        Integer ekspozituraId,
        String nazivEkspoziture, // npr. "Beograd - Aerodrom"
        Double satnica
) {
}
