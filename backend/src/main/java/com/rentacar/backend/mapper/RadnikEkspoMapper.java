package com.rentacar.backend.mapper;


import com.rentacar.backend.dto.CreateRadnikEkspoDTO;
import com.rentacar.backend.dto.RadnikEkspoDTO;
import com.rentacar.backend.model.RadnikEkspo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RadnikEkspoMapper {
    // Mapira Entitet -> DTO
    @Mapping(source = "radnik.radnikID", target = "radnikId")
    @Mapping(target = "radnikImePrezime", expression = "java(radnikEkspo.getRadnik().getIme() + \" \" + radnikEkspo.getRadnik().getPrezime())")
    @Mapping(source = "ekspozitura.ekspozituraID", target = "ekspozituraId")
    @Mapping(source = "ekspozitura.naziv", target = "nazivEkspoziture")
    RadnikEkspoDTO toDTO(RadnikEkspo radnikEkspo);

    // Mapira CreateDTO -> Entitet
    // Napomena: Polja id.radnikID i id.ekspozituraID mapiramo direktno u ugnježdeni ključ
    @Mapping(source = "radnikId", target = "id.radnikID")
    @Mapping(source = "ekspozituraId", target = "id.ekspozituraID")
    @Mapping(target = "radnik", ignore = true)      // Postavlja se u servisu (fetch iz baze)
    @Mapping(target = "ekspozitura", ignore = true) // Postavlja se u servisu (fetch iz baze)
    RadnikEkspo toEntity(CreateRadnikEkspoDTO dto);
}
