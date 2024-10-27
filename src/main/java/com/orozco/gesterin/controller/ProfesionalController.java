package com.orozco.gesterin.controller;

import com.orozco.gesterin.model.Profesional;
import com.orozco.gesterin.service.ProfesionalService;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 22 oct. 2024
 * @description Sistema GESTERIN
 */
public class ProfesionalController {

    private final ProfesionalService profecionalService;

    public ProfesionalController(ProfesionalService profesionalService) {
        this.profecionalService = profesionalService;
    }

    public ProfesionalService getProfesionalService() {
        return this.profecionalService;
    }

    /**
     * Buscar profesional por id
     *
     * @param idProfesional: identificador del profesional
     * @return Profesional
     */
    public Profesional findById(Long idProfesional) {
        return this.profecionalService.findById(idProfesional);
    }
}
