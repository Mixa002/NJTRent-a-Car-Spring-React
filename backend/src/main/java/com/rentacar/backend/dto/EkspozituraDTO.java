package com.rentacar.backend.dto;

public record EkspozituraDTO(
        Integer ekspozituraID,
        String naziv,
        String adresa,
        Integer gradId,
        String nazivGrada
) {}
