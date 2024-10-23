
package com.orozco.gesterin.service;

import com.orozco.gesterin.model.Profesional;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 22 oct. 2024
 * @description Sistema GESTERIN
 */
public interface ProfesionalService {

    /**
     * Buscar profesional por id
     * @param idProfesional: identificador del profesional
     * @return Profesional
     */
    Profesional findById(Long idProfesional);
}
