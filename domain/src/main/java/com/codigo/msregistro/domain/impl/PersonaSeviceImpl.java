package com.codigo.msregistro.domain.impl;

import com.codigo.msregistro.domain.aggregates.dto.PersonaDto;
import com.codigo.msregistro.domain.aggregates.request.PersonaRequest;
import com.codigo.msregistro.domain.ports.in.PersonaServiceIn;
import com.codigo.msregistro.domain.ports.out.PersonaServiceOut;
import org.springframework.stereotype.Service;

@Service
public class PersonaSeviceImpl implements PersonaServiceIn {

    private final PersonaServiceOut personaServiceOut;

    public PersonaSeviceImpl(PersonaServiceOut personaServiceOut) {
        this.personaServiceOut = personaServiceOut;
    }
    @Override
    public PersonaDto crearPersonaIn(PersonaRequest personaRequest) {
        return personaServiceOut.crearPersonaOut(personaRequest);
    }
}
