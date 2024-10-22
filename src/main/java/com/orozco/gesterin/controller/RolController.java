package com.orozco.gesterin.controller;

import com.orozco.gesterin.DAO.Implementaciones.RolDAO;
import com.orozco.gesterin.model.Rol;
import com.orozco.gesterin.service.Implement.RolServiceImpl;
import com.orozco.gesterin.service.RolService;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class RolController {

    private final RolService rolService;

    public RolController() {
        this.rolService = new RolServiceImpl();
    }

    public List<Rol> findAll(){
        return this.rolService.findAll();
    }
   
}
