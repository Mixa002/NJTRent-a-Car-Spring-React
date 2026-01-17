package com.rentacar.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ekspozitura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ekspozituraID;

    private String naziv;
    private String adresa;

    @ManyToOne
    @JoinColumn(name = "grad_id")
    private Grad grad;
}
