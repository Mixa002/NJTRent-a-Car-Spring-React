package com.rentacar.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Radnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer radnikID;

    private String ime;
    private String prezime;

    @Column(unique = true)
    private String korisnickoIme;

    @Column(unique = true)
    private String email;

    private String lozinka;

}
