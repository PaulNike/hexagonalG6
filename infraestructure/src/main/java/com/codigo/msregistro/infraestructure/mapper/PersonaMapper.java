package com.codigo.msregistro.infraestructure.mapper;

import com.codigo.msregistro.domain.aggregates.dto.PersonaDto;
import com.codigo.msregistro.infraestructure.entity.PersonaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonaMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public PersonaDto mapToDto(PersonaEntity personaEntity){
        return MODEL_MAPPER.map(personaEntity, PersonaDto.class);
    }
    public PersonaEntity mapToEntity(PersonaDto personaDto){
        return MODEL_MAPPER.map(personaDto, PersonaEntity.class);
    }
}
