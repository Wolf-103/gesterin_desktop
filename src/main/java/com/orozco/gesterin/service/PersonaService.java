package com.orozco.gesterin.service;

import com.orozco.gesterin.DAO.Implementaciones.PersonaDAO;
import com.orozco.gesterin.model.Persona;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 26 oct. 2024
 * @description Sistema GESTERIN
 */
public interface PersonaService {

    public PersonaDAO getPersonaDAO();

    List<Persona> findPersonaUsuario();

    Persona save(Persona persona);

    List<Persona> findAll();

    List<Persona> findByParams(String params);

    Persona update(Persona persona);

    boolean delete(Long idPersona);

    /**
     * Validar si existe profesional con el id
     *
     * @param id: identifiador del profesional
     * @return
     */
    public boolean existsById(Long id);

    /**
     * Validar si existe persona con el id
     *
     * @param email : email de la persona
     * @return boolean
     */
    boolean existsByEmail(String email);

    /**
     * Validar si existe persona con el telefono
     *
     * @param telefono : email de la persona
     * @return boolean
     */
    boolean existsByTelephone(String telefono);
}
