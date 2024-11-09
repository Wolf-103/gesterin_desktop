package com.orozco.gesterin.service;

import com.orozco.gesterin.model.Administrador;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 26 oct. 2024
 * @description Sistema GESTERIN
 */
public interface AdministradorService {

    Administrador save(Administrador administrador);

    List<Administrador> findAll();

    List<Administrador> findByParams(String params);

    Administrador update(Administrador administrador);

    boolean delete(Long idAdministrador);

    List<Administrador> findPersonaUsersAdministrators();
}
