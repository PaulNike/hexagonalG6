package com.codigo.msregistro.application.controller;

import com.codigo.msregistro.domain.aggregates.request.PersonaRequest;
import com.codigo.msregistro.domain.ports.in.PersonaServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persona/v1")
@RequiredArgsConstructor
public class PersonaController {
    private final PersonaServiceIn personaServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<?> registrarPersona(
            @RequestBody PersonaRequest personaRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(personaServiceIn.crearPersonaIn(personaRequest));
    }

}
