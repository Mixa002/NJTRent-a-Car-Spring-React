package com.rentacar.backend.dto;

import com.rentacar.backend.model.enums.StatusAutomobila;
import com.rentacar.backend.model.enums.VrstaGoriva;
import com.rentacar.backend.model.enums.VrstaMenjaca;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AutomobilDTO(
        Integer automobilID,
        String regBroj,
        String marka,
        String model,
        BigDecimal cenaPoDanu,
        String slikaURL,
        VrstaMenjaca menjac,
        VrstaGoriva gorivo,
        Integer godiste,
        StatusAutomobila status,
        Integer ekspozituraId,
        String nazivEkspoziture// Dodato radi preglednosti na frontendu
//        LocalDateTime izmenjen   // Korisno za prikaz poslednjeg ažuriranja
) {}
