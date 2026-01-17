package com.rentacar.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class RadnikEkspo {
    @EmbeddedId
    private RadnikEkspoKey id = new RadnikEkspoKey();

    @ManyToOne
    @MapsId("radnikID")
    @JoinColumn(name = "radnik_id")
    private Radnik radnik;

    @ManyToOne
    @MapsId("ekspozituraID")
    @JoinColumn(name = "ekspozitura_id")
    private Ekspozitura ekspozitura;

    private Double satnica;
}
