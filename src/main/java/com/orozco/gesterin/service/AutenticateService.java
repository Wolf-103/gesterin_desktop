package com.orozco.gesterin.service;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 16 oct. 2024
 * @description Sistema GESTERIN
 */
public interface AutenticateService {

    /**
     * Encriptar contraseña
     *
     * @param password
     * @return
     */
    String hashPassword(String password);

    /**
     * Validación de contraseña, utilizando métodos de la librería BCypt
     *
     * @param originalPassword: tipo String contraseña enviada por usuario
     * @param storedPasswordHash: tipo
     * @return
     */
    boolean validatePassword(String originalPassword, String storedPasswordHash);

    /**
     * Validación de existencia de usaurio y comprobación de contraseña
     * @param nombre
     * @param contrasena
     * @return 
     */
    boolean authenticate(String nombre, String contrasena);
}
