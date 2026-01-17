package com.rentacar.backend.mapper;


import com.rentacar.backend.dto.CreateRadnikDTO;
import com.rentacar.backend.dto.RadnikDTO;
import com.rentacar.backend.model.Radnik;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RadnikMapper {
    // Mapira Entitet -> DTO (za GET zahtev)
    // MapStruct automatski prepoznaje ista imena polja (ime, prezime, email...)
    RadnikDTO toDTO(Radnik radnik);

    // Mapira CreateDTO -> Entitet (za POST zahtev)
    @Mapping(target = "radnikID", ignore = true)
    Radnik toEntity(CreateRadnikDTO dto);
}
