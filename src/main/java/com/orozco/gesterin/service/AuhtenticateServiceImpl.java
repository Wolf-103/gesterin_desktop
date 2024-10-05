package com.orozco.gesterin.service;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author crist
 */
public class AuhtenticateServiceImpl {
    // Método para encriptar la contraseña
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Método para validar la contraseña
    public static boolean validatePassword(String originalPassword, String storedPasswordHash) {
        return BCrypt.checkpw(originalPassword, storedPasswordHash);
    }
}
