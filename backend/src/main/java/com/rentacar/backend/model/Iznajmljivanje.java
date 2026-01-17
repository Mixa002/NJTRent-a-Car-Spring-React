package com.rentacar.backend.model;

import com.rentacar.backend.model.enums.StatusIznajmljivanja;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
public class Iznajmljivanje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iznajmljivanjeID;


    private BigDecimal ukupanIznos;
    private String opis;

    @Enumerated(EnumType.STRING)
    private StatusIznajmljivanja status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime datumKreiranja;


    @ManyToOne
    @JoinColumn(name = "klijent_id")
    private Klijent klijent;

    @OneToMany(mappedBy = "iznajmljivanje", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StavkaIznajmljivanja> stavke = new ArrayList<>();

}
