package com.rentacar.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class StavkaIznajmljivanja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stavkaID;

    private LocalDate datumOd;
    private LocalDate datumDo;
    private BigDecimal cenaStavke;
    private String napomena;

    @ManyToOne
    @JoinColumn(name = "iznajmljivanje_id")
    private Iznajmljivanje iznajmljivanje;

    @ManyToOne
    @JoinColumn(name = "automobil_id")
    private Automobil automobil;
}
