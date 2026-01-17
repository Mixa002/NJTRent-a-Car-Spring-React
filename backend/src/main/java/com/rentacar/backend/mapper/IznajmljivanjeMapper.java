package com.rentacar.backend.mapper;

import com.rentacar.backend.dto.CreateIznajmljivanjeDTO;
import com.rentacar.backend.dto.IznajmljivanjeDTO;
import com.rentacar.backend.model.Iznajmljivanje;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {StavkaIznajmljivanjaMapper.class})
public interface IznajmljivanjeMapper {

    // Mapiranje u DTO - dodata provera za null
    @Mapping(target = "klijentImePrezime", expression = "java(iznajmljivanje.getKlijent() != null ? " +
            "iznajmljivanje.getKlijent().getIme() + \" \" + iznajmljivanje.getKlijent().getPrezime() : null)")
    IznajmljivanjeDTO toDTO(Iznajmljivanje iznajmljivanje);

    // Mapiranje iz CreateDTO u Entitet
    @Mapping(target = "iznajmljivanjeID", ignore = true)
    @Mapping(target = "ukupanIznos", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "datumKreiranja", ignore = true)
    @Mapping(target = "klijent", ignore = true)
    Iznajmljivanje toEntity(CreateIznajmljivanjeDTO dto);
}
