package com.rentacar.backend.dto;

public record RadnikDTO(
        Integer radnikID,
        String ime,
        String prezime,
        String korisnickoIme,
        String email
) {
}
