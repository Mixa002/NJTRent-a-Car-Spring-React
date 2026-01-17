package com.rentacar.backend.dto;

import com.rentacar.backend.model.enums.StatusAutomobila;
import com.rentacar.backend.model.enums.VrstaGoriva;
import com.rentacar.backend.model.enums.VrstaMenjaca;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateAutomobilDTO(
        @NotBlank(message = "Registarski broj je obavezan")
        String regBroj,

        @NotBlank(message = "Marka je obavezna")
        String marka,

        @NotBlank(message = "Model je obavezan")
        String model,

        @NotNull(message = "Cena po danu je obavezna")
        @DecimalMin(value = "0.0", inclusive = false, message = "Cena mora biti veća od 0")
        BigDecimal cenaPoDanu,

        String slikaURL,

        @NotNull(message = "Vrsta menjača je obavezna")
        VrstaMenjaca menjac,

        @NotNull(message = "Vrsta goriva je obavezna")
        VrstaGoriva gorivo,

        @Min(value = 1900, message = "Godište ne može biti pre 1900.")
        Integer godiste,

        @NotNull(message = "Status je obavezan")
        StatusAutomobila status,

        @NotNull(message = "Morate dodeliti automobil ekspozituri")
        Integer ekspozituraId
) {}
