package controller;

import com.orozco.gesterin.model.Paciente;
import com.orozco.gesterin.persistence.PacienteDao;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class PacienteController {

    private final PacienteDao pacienteDao;

    public PacienteController() {
        this.pacienteDao = new PacienteDao();
    }

    public boolean registrarpaciente(Paciente paciente) {
        return this.pacienteDao.registrar(paciente);
    }

}
