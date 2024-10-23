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
     *
     * @param idProfesional: identificador del profesional
     * @param idEspecialidad: identificador de la especialidad
     * @return boolean
     */
    boolean addEspecialidadToProfesional(Long idProfesional, Long idEspecialidad);

    /**
     * Validar si existe profesional con el id
     *
     * @param id: identifiador del profesional
     * @return
     */
    boolean existsById(Long id);

    /**
     * Des asignar especialidad en un profesional
     *
     * @param idProfesional: identificador del profesional
     * @param idEspecialidad: identificador de la especialidad
     * @return boolean
     */
    boolean deleteEspecialidadToProfesional(Long idProfesional, Long idEspecialidad);

    /**
     * Agregar especialidades en grupo a un profesional determinado
     *
     * @param idProfesional: identificador del profesionar
     * @param idEspecialidades: lista de id's Long de las especialidades a
     * agregar
     * @return booelan
     */
    boolean addEspecialidadesToProfesional(Long idProfesional, List<Long> idEspecialidades);

    /**
     * Eliminar especialidades en grupo a un profesional determinado
     *
     * @param idProfesional: identificador del profesionar
     * @param idEspecialidades: lista de id's Long de las especialidades a
     * eliminar
     * @return booelan
     */
    boolean deleteEspecialidadesFromProfesional(Long idProfesional, List<Long> idEspecialidades);

}
