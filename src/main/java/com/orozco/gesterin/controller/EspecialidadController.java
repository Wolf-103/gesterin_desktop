package com.orozco.gesterin.controller;

import com.orozco.gesterin.DAO.Implementaciones.ProfesionalDAO;
import com.orozco.gesterin.model.Especialidad;
import com.orozco.gesterin.service.EspecialidadService;
import com.orozco.gesterin.service.Implement.EspecialidadServiceImpl;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    public EspecialidadController(ProfesionalDAO profesionalDAO) {
        this.especialidadService = new EspecialidadServiceImpl(profesionalDAO);
    }

    /**
     * Buscar todas las especialidades
     *
     * @return
     */
    public List<Especialidad> findAll() {
        return this.especialidadService.findAll();
    }

    /**
     * Asignar especialidad a un profesional
     *
     * @param idProfesional: identificador del profesional
     * @param idEspecialidad: identificador de la especialidad
     * @return boolean
     */
    public boolean addEspecialidadToProfesional(Long idProfesional, Long idEspecialidad) {
        return this.especialidadService.addEspecialidadToProfesional(idProfesional, idEspecialidad);
    }

    /**
     * Des asignar especialidad en un profesional
     *
     * @param idProfesional: identificador del profesional
     * @param idEspecialidad: identificador de la especialidad
     * @return boolean
     */
    public boolean deleteEspecialidadToProfesional(Long idProfesional, Long idEspecialidad) {
        return this.especialidadService.deleteEspecialidadToProfesional(idProfesional, idEspecialidad);
    }

    /**
     * Agregar especialidades en grupo a un profesional determinado
     *
     * @param idProfesional: identificador del profesionar
     * @param idEspecialidades: lista de id's Long de las especialidades a
     * agregar
     * @return
     */
    public boolean addEspecialidadesToProfesional(Long idProfesional, List<Long> idEspecialidades) {
        return this.especialidadService.addEspecialidadesToProfesional(idProfesional, idEspecialidades);
    }

    /**
     * Eliminar especialidades en grupo a un profesional determinado
     *
     * @param idProfesional: identificador del profesionar
     * @param idEspecialidades: lista de id's Long de las especialidades a
     * eliminar
     * @return booelan
     */
    public boolean deleteEspecialidadesFromProfesional(Long idProfesional, List<Long> idEspecialidades) {
        return this.especialidadService.deleteEspecialidadesFromProfesional(idProfesional, idEspecialidades);
    }
}
