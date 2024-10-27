package com.orozco.gesterin.service;

import com.orozco.gesterin.model.Usuario;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 16 oct. 2024
 * @description Sistema GESTERIN
 */
public interface AutenticateService {

    /**
     * Validación de existencia de usaurio y comprobación de contraseña
     * @param nombre
     * @param contrasena
     * @return 
     */
    Usuario authenticate(String nombre, String contrasena);
}
