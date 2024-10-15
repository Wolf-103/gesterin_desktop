package com.orozco.gesterin.dto;

import com.orozco.gesterin.model.Persona;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 15 oct. 2024
 * @description Sistema GESTERIN
 */
public class PersonaDTO extends Persona {

    public PersonaDTO(String nombre, String apellido, String email, String telefono) {
        super(nombre, apellido, email, telefono);
    }

    public PersonaDTO() {
    }

}
