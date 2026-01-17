package com.rentacar.backend.mapper;


import com.rentacar.backend.dto.CreateStavkaIznajmljivanjaDTO;
import com.rentacar.backend.dto.StavkaIznajmljivanjaDTO;
import com.rentacar.backend.model.StavkaIznajmljivanja;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StavkaIznajmljivanjaMapper {
    @Mapping(source = "automobil.automobilID", target = "automobilId")
    @Mapping(target = "automobilInfo", expression = "java(stavka.getAutomobil() != null ? " +
            "stavka.getAutomobil().getMarka() + \" \" + stavka.getAutomobil().getModel() + \" (\" + stavka.getAutomobil().getRegBroj() + \")\" : null)")
    StavkaIznajmljivanjaDTO toDTO(StavkaIznajmljivanja stavka);

    @Mapping(target = "stavkaID", ignore = true)
    @Mapping(target = "iznajmljivanje", ignore = true)
    @Mapping(target = "automobil", ignore = true)
    @Mapping(target = "cenaStavke", ignore = true)
    StavkaIznajmljivanja toEntity(CreateStavkaIznajmljivanjaDTO dto);
}
