package com.app.cias.service.mappers;

import com.app.cias.model.Persona;
import com.app.cias.service.dtos.PersonaResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
        PersonaResponseDTO toResponseDTO (Persona persona);
}
