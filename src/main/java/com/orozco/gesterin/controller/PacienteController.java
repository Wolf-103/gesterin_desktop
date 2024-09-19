package com.orozco.gesterin.controller;

import com.orozco.gesterin.model.Paciente;
import com.orozco.gesterin.repositories.PacienteRepository;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class PacienteController {

    private final PacienteRepository pacienteRepo;

    public PacienteController() {
        this.pacienteRepo = new PacienteRepository();
    }

    public boolean registrarpaciente(Paciente paciente) {
        return this.pacienteRepo.registrar(paciente);
    }
    
    public List<Paciente> getAll(){
        return this.pacienteRepo.getAll();
    }
    
    public boolean update(Paciente paciente){
        return this.pacienteRepo.update(paciente);
    }

}
