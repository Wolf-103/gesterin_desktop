package com.orozco.gesterin.controller;

import com.orozco.gesterin.DAO.Implementaciones.UsuarioDAO;
import com.orozco.gesterin.model.Usuario;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class UsuarioController {

    private final UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    

}
