package com.orozco.gesterin.service.Implement;

import com.orozco.gesterin.DAO.Implementaciones.PersonaDAO;
import com.orozco.gesterin.DAO.Implementaciones.ProfesionalDAO;
import com.orozco.gesterin.model.Profesional;
import com.orozco.gesterin.service.ProfesionalService;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 22 oct. 2024
 * @description Sistema GESTERIN
 */
public class ProfesionalServiceImpl implements ProfesionalService{
        private final ProfesionalDAO profesionalDAO;


    public ProfesionalServiceImpl() {
        this.profesionalDAO = new ProfesionalDAO(new PersonaDAO());
    }

    /**
     * Buscar profesional por id
     * @param idProfesional: identificador del profesional
     * @return Profesional
     */
        @Override
    public Profesional findById(Long idProfesional){
        return this.profesionalDAO.findById(idProfesional);
    }
    
}
