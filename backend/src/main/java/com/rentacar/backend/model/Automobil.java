package com.rentacar.backend.model;
import com.rentacar.backend.model.enums.StatusAutomobila;
import com.rentacar.backend.model.enums.VrstaGoriva;
import com.rentacar.backend.model.enums.VrstaMenjaca;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Data
public class Automobil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer automobilID;

    @Column(unique = true, name = "registarski_broj")
    private String regBroj;

    private String marka;
    private String model;
    private BigDecimal cenaPoDanu;

    @Column(unique = true)
    private String slikaURL;

    @CreationTimestamp
    private LocalDateTime kreiran;

    @UpdateTimestamp
    private LocalDateTime izmenjen;


    @Enumerated(EnumType.STRING)
    private VrstaMenjaca menjac;

    @Enumerated(EnumType.STRING)
    private VrstaGoriva gorivo;

    private Integer godiste;

    @Enumerated(EnumType.STRING)
    private StatusAutomobila status;

    @ManyToOne
    @JoinColumn(name = "ekspozitura_id")
    private Ekspozitura trenutnaEkspozitura;

}
