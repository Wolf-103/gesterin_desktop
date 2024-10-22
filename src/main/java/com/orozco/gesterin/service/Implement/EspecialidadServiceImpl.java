package com.orozco.gesterin.service.Implement;

import com.orozco.gesterin.DAO.Implementaciones.EspecialidadDAO;
import com.orozco.gesterin.DAO.Implementaciones.PersonaDAO;
import com.orozco.gesterin.DAO.Implementaciones.ProfesionalDAO;
import com.orozco.gesterin.model.Especialidad;
import com.orozco.gesterin.service.EspecialidadService;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 22 oct. 2024
 * @description Sistema GESTERIN
 */
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadDAO especialidadDAO;
    private final ProfesionalDAO profesionalDAO;

    public EspecialidadServiceImpl() {
        this.especialidadDAO = new EspecialidadDAO();
        this.profesionalDAO = new ProfesionalDAO(new PersonaDAO());
    }

    /**
     * Busca todas las especialidades
     * @return 
     */
    @Override
    public List<Especialidad> findAll() {
        return this.especialidadDAO.findAll();
    }

    /**
     * Asignar especialidad a un profesional
     * @param idProfesional: identificador del profesional
     * @param idEspecialidad: identificador de la especialidad
     * @return boolean
     */
    public boolean addEspecialidadToProfesional(Long idProfesional, Long idEspecialidad) {
        if (this.especialidadDAO.findById(idEspecialidad) != null
                && this.profesionalDAO.findById(idProfesional) != null) {
            return this.especialidadDAO.addEspecialidadToProfesional(idProfesional, idEspecialidad);
        }
        return false;
    }
}
