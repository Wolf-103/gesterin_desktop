package com.orozco.gesterin.service.Implement;

import com.orozco.gesterin.DAO.Implementaciones.AdministradorDAO;
import com.orozco.gesterin.DAO.Implementaciones.PersonaDAO;
import com.orozco.gesterin.DAO.Implementaciones.UsuarioDAO;
import com.orozco.gesterin.model.Administrador;
import com.orozco.gesterin.service.AdministradorService;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 26 oct. 2024
 * @description Sistema GESTERIN
 */
public class AdministradorServiceImpl implements AdministradorService {

    private final AdministradorDAO administradorDAO;

    public AdministradorServiceImpl(PersonaDAO personaDAO, UsuarioDAO usuarioDAO) {
        this.administradorDAO = new AdministradorDAO(personaDAO, usuarioDAO);
    }

    @Override
    public Administrador save(Administrador administrador) {
        return this.administradorDAO.save(administrador);
    }

    @Override
    public List<Administrador> findAll() {
        return this.administradorDAO.findAll();
    }

    @Override
    public List<Administrador> findByParams(String params) {
        return this.administradorDAO.findAllByParams(params);
    }

    @Override
    public Administrador update(Administrador administrador) {
        return this.administradorDAO.update(administrador);
    }

    @Override
    public boolean delete(Long idAdministrador) {
        return this.administradorDAO.delete(idAdministrador);
    }

}
