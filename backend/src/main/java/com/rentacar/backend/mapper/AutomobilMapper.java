package com.rentacar.backend.mapper;


import com.rentacar.backend.dto.AutomobilDTO;
import com.rentacar.backend.dto.CreateAutomobilDTO;
import com.rentacar.backend.model.Automobil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutomobilMapper {
    // Mapira Entitet u DTO (za GET zahtev)
    // Ovde govorimo MapStruct-u da uzme naziv iz objekta ekspoziture
    @Mapping(source = "trenutnaEkspozitura.naziv", target = "nazivEkspoziture")
    @Mapping(source = "trenutnaEkspozitura.ekspozituraID", target = "ekspozituraId")
    AutomobilDTO toDTO(Automobil automobil);

    // Mapira CreateDTO u Entitet (za POST zahtev)
    // Ignorišemo ID i ostale stvari koje ne dolaze iz DTO-a
    @Mapping(target = "automobilID", ignore = true)
    @Mapping(target = "kreiran", ignore = true)
    @Mapping(target = "izmenjen", ignore = true)
    @Mapping(target = "trenutnaEkspozitura", ignore = true) // Ovo ćemo setovati ručno u servisu
    Automobil toEntity(CreateAutomobilDTO dto);
}
