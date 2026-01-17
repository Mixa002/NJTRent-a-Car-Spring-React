package com.rentacar.backend.mapper;


import com.rentacar.backend.dto.CreateGradDTO;
import com.rentacar.backend.dto.GradDTO;
import com.rentacar.backend.model.Grad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GradMapper {
    GradDTO toDTO(Grad grad);

    @Mapping(target = "gradID", ignore = true)
    @Mapping(target = "ekspoziture", ignore = true)
    Grad toEntity(CreateGradDTO dto);
}
