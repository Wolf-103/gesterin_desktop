package com.orozco.gesterin.service.Implement;

import com.orozco.gesterin.DAO.Implementaciones.RolDAO;
import com.orozco.gesterin.model.Rol;
import com.orozco.gesterin.service.RolService;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 22 oct. 2024
 * @description Sistema GESTERIN
 */
public class RolServiceImpl implements RolService{

    private final RolDAO rolDAO;

    public RolServiceImpl() {
        this.rolDAO = new RolDAO();
    }

    @Override
    public List<Rol> findAll() {
        return this.rolDAO.findAll();
    }
}
