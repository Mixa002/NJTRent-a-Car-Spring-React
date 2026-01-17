package com.rentacar.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Grad {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)

    private Integer gradID;
    private String naziv;

    @Column( name = "postanski_broj" , unique = true)
    private String postanskiBroj;

    @OneToMany(mappedBy = "grad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ekspozitura> ekspoziture = new ArrayList<>();


}
