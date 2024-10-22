package com.orozco.gesterin.service;

import com.orozco.gesterin.model.Especialidad;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 22 oct. 2024
 * @description Sistema GESTERIN
 */
public interface EspecialidadService {
    
    List<Especialidad> findAll();
    
    /**
     * Asignar especialidad a un profesional
     * @param idProfesional: identificador del profesional
     * @param idEspecialidad: identificador de la especialidad
     * @return boolean
     */
    boolean addEspecialidadToProfesional(Long idProfesional, Long idEspecialidad);

}
