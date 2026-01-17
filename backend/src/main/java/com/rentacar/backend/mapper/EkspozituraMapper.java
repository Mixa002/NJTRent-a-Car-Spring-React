package com.rentacar.backend.mapper;

import com.rentacar.backend.dto.CreateEkspozituraDTO;
import com.rentacar.backend.dto.EkspozituraDTO;
import com.rentacar.backend.model.Ekspozitura;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EkspozituraMapper {

    // Iz entiteta u DTO
    // source ide u: entitet Ekspozitura -> polje grad -> polje gradID (iz klase Grad)
    @Mapping(source = "grad.gradID", target = "gradId")
    @Mapping(source = "grad.naziv", target = "nazivGrada")
    EkspozituraDTO toDTO(Ekspozitura ekspozitura);

    // Iz DTO u entitet
    // Ovde IGNORIŠEMO grad jer ćemo ga rucno povezati u servisu
    @Mapping(target = "grad", ignore = true)
    Ekspozitura toEntity(CreateEkspozituraDTO dto);
}