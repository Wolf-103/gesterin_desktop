package com.orozco.gesterin.service.Implement;

import com.orozco.gesterin.DAO.Implementaciones.EspecialidadDAO;
import com.orozco.gesterin.DAO.Implementaciones.PersonaDAO;
import com.orozco.gesterin.DAO.Implementaciones.ProfesionalDAO;
import com.orozco.gesterin.DAO.Implementaciones.UsuarioDAO;
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

    public EspecialidadServiceImpl(ProfesionalDAO profesionalDAO) {
        this.especialidadDAO = new EspecialidadDAO();
        this.profesionalDAO = profesionalDAO;
    }

    /**
     * Busca todas las especialidades
     *
     * @return
     */
    @Override
    public List<Especialidad> findAll() {
        return this.especialidadDAO.findAll();
    }

    /**
     * Asignar especialidad a un profesional
     *
     * @param idProfesional: identificador del profesional
     * @param idEspecialidad: identificador de la especialidad
     * @return boolean
     */
    @Override
    public boolean addEspecialidadToProfesional(Long idProfesional, Long idEspecialidad) {
        if (this.especialidadDAO.findById(idEspecialidad) != null
                && this.profesionalDAO.findById(idProfesional) != null) {
            return this.especialidadDAO.addEspecialidadToProfesional(idProfesional, idEspecialidad);
        }
        return false;
    }

    /**
     * Validar si existe profesional con el id
     *
     * @param id: identifiador del profesional
     * @return
     */
    @Override
    public boolean existsById(Long id) {
        return this.especialidadDAO.existsById(id);
    }

    /**
     * Des asignar especialidad en un profesional
     *
     * @param idProfesional: identificador del profesional
     * @param idEspecialidad: identificador de la especialidad
     * @return boolean
     */
    @Override
    public boolean deleteEspecialidadToProfesional(Long idProfesional, Long idEspecialidad) {
        return this.especialidadDAO.deleteEspecialidadToProfesional(idProfesional, idEspecialidad);
    }

    /**
     * Agregar especialidades en grupo a un profesional determinado
     *
     * @param idProfesional: identificador del profesionar
     * @param idEspecialidades: lista de id's Long de las especialidades a
     * agregar
     * @return
     */
    @Override
    public boolean addEspecialidadesToProfesional(Long idProfesional, List<Long> idEspecialidades) {
        return this.especialidadDAO.addEspecialidadesToProfesional(idProfesional, idEspecialidades);
    }

    /**
     * Eliminar especialidades en grupo a un profesional determinado
     *
     * @param idProfesional: identificador del profesionar
     * @param idEspecialidades: lista de id's Long de las especialidades a
     * eliminar
     * @return booelan
     */
    @Override
    public boolean deleteEspecialidadesFromProfesional(Long idProfesional, List<Long> idEspecialidades) {
        return this.especialidadDAO.deleteEspecialidadesFromProfesional(idProfesional, idEspecialidades);
    }
}
