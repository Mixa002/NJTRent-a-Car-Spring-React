package com.rentacar.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor // Potrebno za Hibernate
@AllArgsConstructor // Potrebno za tvoj Controller: new RadnikEkspoKey(id1, id2)
public class RadnikEkspoKey implements Serializable{
   @Column(name = "radnik_id")
    private Integer radnikID;
   @Column(name = "ekspozitura_id")
    private Integer ekspozituraID;
}
