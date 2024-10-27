package com.orozco.gesterin.service.Implement;

import com.orozco.gesterin.DAO.Implementaciones.PersonaDAO;
import com.orozco.gesterin.model.Persona;
import com.orozco.gesterin.service.PersonaService;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 26 oct. 2024
 * @description Sistema GESTERIN
 */
public class PersonServiceImpl implements PersonaService {

    private final PersonaDAO personaDAO;

    public PersonServiceImpl() {
        this.personaDAO = new PersonaDAO();
    }

    @Override
    public PersonaDAO getPersonaDAO() {
        return this.personaDAO;
    }

    @Override
    public List<Persona> findPersonaUsuario() {
        return this.personaDAO.findAllPeopleUsers();
    }

    @Override
    public Persona save(Persona persona) {
        if(this.personaDAO.existsByEmail(persona.getTelefono()))
            return this.personaDAO.save(persona);
        return null;
    }

    @Override
    public List<Persona> findAll() {
        return this.personaDAO.findAll();
    }

    @Override
    public List<Persona> findByParams(String params) {
        return this.personaDAO.findAllByParams(params);
    }

    @Override
    public Persona update(Persona persona) {
        return this.personaDAO.update(persona);
    }

    @Override
    public boolean delete(Long idPersona) {
        return this.personaDAO.delete(idPersona);
    }

    @Override
    public boolean existsById(Long id) {
        return this.personaDAO.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.personaDAO.existsByEmail(email);
    }
    
      /**
     * Validar si existe persona con el telefono
     *
     * @param telefono : email de la persona
     * @return boolean
     */
    @Override
    public boolean existsByTelephone(String telefono){
        return this.personaDAO.existsByTelephone(telefono);
    }

}
