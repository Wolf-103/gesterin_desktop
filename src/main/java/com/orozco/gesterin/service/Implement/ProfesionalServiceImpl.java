package com.orozco.gesterin.service.Implement;

import com.orozco.gesterin.DAO.Implementaciones.PersonaDAO;
import com.orozco.gesterin.DAO.Implementaciones.ProfesionalDAO;
import com.orozco.gesterin.DAO.Implementaciones.UsuarioDAO;
import com.orozco.gesterin.model.Profesional;
import com.orozco.gesterin.service.ProfesionalService;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 22 oct. 2024
 * @description Sistema GESTERIN
 */
public class ProfesionalServiceImpl implements ProfesionalService {

    private final ProfesionalDAO profesionalDAO;

    public ProfesionalServiceImpl(PersonaDAO personaDAO, UsuarioDAO usuarioDAO) {
        this.profesionalDAO = new ProfesionalDAO(personaDAO, usuarioDAO);
    }

    /**
     * Buscar profesional por id
     *
     * @param idProfesional: identificador del profesional
     * @return Profesional
     */
    @Override
    public Profesional findById(Long idProfesional) {
        return this.profesionalDAO.findById(idProfesional);
    }

    @Override
    public Profesional save(Profesional profesional) {
        return this.profesionalDAO.save(profesional);
    }

    @Override
    public ProfesionalDAO getProfesionalDAO() {
        return this.profesionalDAO;
    }

    @Override
    public Profesional update(Profesional profesional) {
        return this.profesionalDAO.update(profesional);
    }

    @Override
    public List<Profesional> findPersonaProfesionals() {
        return this.profesionalDAO.findAll();
    }

}
