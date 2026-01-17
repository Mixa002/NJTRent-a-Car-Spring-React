package com.rentacar.backend.dto;

public record KlijentDTO(
        Integer klijentID,
        String ime,
        String prezime,
        String email,
        String korisnickoIme,
        String brTelefona,
        String punoIme, // Opciono: olakšava posao frontend-u
        String jmbg
) {}
