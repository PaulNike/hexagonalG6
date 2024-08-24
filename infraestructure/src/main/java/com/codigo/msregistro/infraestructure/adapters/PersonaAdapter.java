package com.codigo.msregistro.infraestructure.adapters;

import com.codigo.msregistro.domain.aggregates.constants.Constants;
import com.codigo.msregistro.domain.aggregates.dto.PersonaDto;
import com.codigo.msregistro.domain.aggregates.request.PersonaRequest;
import com.codigo.msregistro.domain.aggregates.response.ResponseReniec;
import com.codigo.msregistro.domain.ports.out.PersonaServiceOut;
import com.codigo.msregistro.infraestructure.dao.PersonaRepository;
import com.codigo.msregistro.infraestructure.entity.PersonaEntity;
import com.codigo.msregistro.infraestructure.mapper.PersonaMapper;
import com.codigo.msregistro.infraestructure.rest.ReniecClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaServiceOut {

    private final PersonaRepository personaRepository;
    private final ReniecClient reniecClient;
    private final PersonaMapper personaMapper;

    @Value("${token.api}")
    private String token;
    @Override
    public PersonaDto crearPersonaOut(PersonaRequest personaRequest) {
        try {
            PersonaEntity persona = getEntity(personaRequest);
            return personaMapper.mapToDto(personaRepository.save(persona));
        }catch (Exception e){
            new RuntimeException(e);
        }
        return null;
    }
    private PersonaEntity getEntity(PersonaRequest personaRequest){
        PersonaEntity personaEntity = new PersonaEntity();

        //Ejecutar la consulta;
        ResponseReniec response = execute(personaRequest.getDni());
        if (Objects.nonNull(response)){
            personaEntity.setNombres(response.getNombres());
            personaEntity.setApellidos(response.getApellidoPaterno()
                    +" "+response.getApellidoMaterno());
            personaEntity.setNumDoc(response.getNumeroDocumento());
            personaEntity.setTipoDoc(response.getTipoDocumento());
            personaEntity.setEstado(Constants.STATUS_ACTIVE);
            personaEntity.setUsua_crea(Constants.USU_CREA);
            personaEntity.setDate_crea(getTime());
            return personaEntity;
        }
        return null;
    }

    private Timestamp getTime(){
        return new Timestamp(System.currentTimeMillis());
    }
    private ResponseReniec execute(String dni){
        String head = "Bearer "+token;
        return reniecClient.getInfoReniec(dni,head);
    }
}
