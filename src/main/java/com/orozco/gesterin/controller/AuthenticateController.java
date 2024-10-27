package com.orozco.gesterin.controller;

import com.orozco.gesterin.model.Usuario;
import com.orozco.gesterin.service.AutenticateService;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 16 oct. 2024
 * @description Sistema GESTERIN
 */
public class AuthenticateController {

    public AuthenticateController(AutenticateService autenticateService) {
        this.autenticateService = autenticateService;
    }

    private final AutenticateService autenticateService;

    /**
     * Validar usuario y credenciales
     * @param nombre: nombre de usuario 
     * @param contrasena: contraseña del usuario
     * @return boolean
     */
    public Usuario autenticate(String nombre, String contrasena) {
        return this.autenticateService.authenticate(nombre, contrasena);
    }

}
