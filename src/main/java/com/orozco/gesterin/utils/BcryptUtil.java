package com.orozco.gesterin.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 26 oct. 2024
 * @description Sistema GESTERIN
 */
public class BcryptUtil {

    /**
     * Encriptar contraseña
     * @param password
     * @return 
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Validación de contraseña, utilizando métodos de la librería BCypt
     *
     * @param originalPassword: tipo String contraseña enviada por usuario
     * @param storedPasswordHash: tipo String hash o contraseña encriptada
     * @return boolean 
     */
    public static boolean validatePassword(String originalPassword, String storedPasswordHash) {
        return BCrypt.checkpw(originalPassword, storedPasswordHash);
    }
}
