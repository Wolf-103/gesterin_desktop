package com.orozco.gesterin.controller;

import com.orozco.gesterin.DAO.Implementaciones.PersonaDAO;
import com.orozco.gesterin.DAO.Implementaciones.UsuarioDAO;
import com.orozco.gesterin.model.Persona;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class UsuarioController {

    private final UsuarioDAO usuarioDAO;
    private final PersonaDAO personaDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
        this.personaDAO = new PersonaDAO();
    }

    public List<Persona> listAllPeopleUsers(){
        return this.personaDAO.findAllPeopleUsers();
    }
    

}
