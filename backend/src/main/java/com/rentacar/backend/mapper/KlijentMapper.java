package com.rentacar.backend.mapper;


import com.rentacar.backend.dto.CreateKlijentDTO;
import com.rentacar.backend.dto.KlijentDTO;
import com.rentacar.backend.model.Klijent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KlijentMapper {
    // Mapira Entitet -> DTO
    // Koristimo default metodu ili izraz da spojimo ime i prezime u punoIme
    @Mapping(target = "punoIme", expression = "java(klijent.getIme() + \" \" + klijent.getPrezime())")
    KlijentDTO toDTO(Klijent klijent);

    // Mapira CreateDTO -> Entitet
    @Mapping(target = "klijentID", ignore = true)
    // Napomena: Lozinku mapiramo ovde, ali ćeš je u servisu verovatno enkriptovati (BCrypt)
    Klijent toEntity(CreateKlijentDTO dto);
}
