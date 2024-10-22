package com.orozco.gesterin.controller;

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

    public EspecialidadController() {
        this.especialidadService = new EspecialidadServiceImpl();
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

}
