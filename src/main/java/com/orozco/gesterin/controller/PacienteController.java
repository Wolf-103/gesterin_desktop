package com.orozco.gesterin.controller;

import com.orozco.gesterin.model.Paciente;
import com.orozco.gesterin.repositories.PacienteRepository;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class PacienteController {

    private final PacienteRepository pacienteDao;

    public PacienteController() {
        this.pacienteDao = new PacienteRepository();
    }

    public boolean registrarpaciente(Paciente paciente) {
        return this.pacienteDao.registrar(paciente);
    }

}
