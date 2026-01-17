package com.rentacar.backend.model;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Klijent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer klijentID;

    private String ime;
    private String prezime;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String jmbg; // DODAJ OVO POLJE

    @Column(unique = true)
    private String korisnickoIme;

    private String lozinka;

    @Column(name = "broj_telefona")
    private String brTelefona;
}
