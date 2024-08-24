package com.codigo.msregistro.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaRequest {
    private String dni;
    private String nombres;
    private String appelidos;

}
