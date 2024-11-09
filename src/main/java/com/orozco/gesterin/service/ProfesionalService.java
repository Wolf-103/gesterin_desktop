package com.orozco.gesterin.service;

import com.orozco.gesterin.DAO.Implementaciones.ProfesionalDAO;
import com.orozco.gesterin.model.Profesional;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 22 oct. 2024
 * @description Sistema GESTERIN
 */
public interface ProfesionalService {

    ProfesionalDAO getProfesionalDAO();

    /**
     * Buscar profesional por id
     *
     * @param idProfesional: identificador del profesional
     * @return Profesional
     */
    Profesional findById(Long idProfesional);

    public Profesional save(Profesional profesional);

    public Profesional update(Profesional profesional);

    List<Profesional> findPersonaProfesionals();
}
