package com.orozco.gesterin.controller;

import com.orozco.gesterin.model.Administrador;
import com.orozco.gesterin.model.Persona;
import com.orozco.gesterin.model.Profesional;
import com.orozco.gesterin.service.AdministradorService;
import com.orozco.gesterin.service.Implement.AdministradorServiceImpl;
import com.orozco.gesterin.service.Implement.PersonServiceImpl;
import com.orozco.gesterin.service.Implement.ProfesionalServiceImpl;
import com.orozco.gesterin.service.Implement.UsuarioServiceImpl;
import com.orozco.gesterin.service.PersonaService;
import com.orozco.gesterin.service.ProfesionalService;
import com.orozco.gesterin.service.UsuarioService;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PersonaService personaService;
    private final AdministradorService administradorService;
    private final ProfesionalService profesionalService;

    public UsuarioController() {
        this.usuarioService = new UsuarioServiceImpl();
        this.personaService = new PersonServiceImpl();
        this.administradorService = new AdministradorServiceImpl(
                this.personaService.getPersonaDAO(),
                this.usuarioService.getUsuarioDAO()
        );
        this.profesionalService = new ProfesionalServiceImpl(
                this.personaService.getPersonaDAO(),
                this.usuarioService.getUsuarioDAO()
        );
    }

    public List<Persona> getAllPeopleUsers() {
        return this.personaService.findPersonaUsuario();
    }

    public List<Administrador> getAllPeopleUserAdministradors() {
        return this.administradorService.findPersonaUsersAdministrators();
    }

    public List<Profesional> getAllPeopleProfessionals() {
        return this.profesionalService.findPersonaProfesionals();
    }

    public boolean validarEmail(String email) {
        return this.personaService.existsByEmail(email);
    }

    public List<Persona> findPersonUserWithParam(String param) {
        return this.personaService.findByParams(param);
    }

    /**
     * Verifica si existe el nombre de usuario
     *
     * @param username: STring nombre del usuario
     * @return boolean true si lo encuentra false si no
     */
    public boolean validarUsername(String username) {
        return this.usuarioService.existByUsername(username);
    }

    /**
     * Validar si existe persona con el telefono
     *
     * @param telefono : email de la persona
     * @return boolean
     */
    public boolean validarTelefono(String telefono) {
        return this.personaService.existsByTelephone(telefono);
    }

    public void bajaUsuario(Persona persona) {
        if (persona != null) {
            if (persona instanceof Administrador admin) {
                admin.getUsuario().setEstado(false);
                admin = this.administradorService.update(admin);
            } else if (persona instanceof Profesional prof) {
                prof.getUsuario().setEstado(false);
                prof = this.profesionalService.update(prof);
            }
        }
    }

    /**
     * Guarda un usuario de clase persona con sus datos de usuario y rol
     * asignado
     *
     * @param persona (puede ser un ADMINISTRADOR O UN PROFESIONAL)
     * @return Persona
     */
    public Persona save(Persona persona) {
        if (persona != null) {
            if (persona instanceof Administrador admin) {
                admin = this.administradorService.save(admin);
                persona = admin;
            } else if (persona instanceof Profesional prof) {
                prof = this.profesionalService.save(prof);
                persona = prof;
            }
        }
        return persona;
    }

    public Persona update(Persona persona) {
        if (persona != null) {
            if (persona instanceof Administrador admin) {
                admin = this.administradorService.update(admin);
                persona = admin;
            } else if (persona instanceof Profesional prof) {
                prof = this.profesionalService.update(prof);
                persona = prof;
            }
        }
        return persona;
    }
}
