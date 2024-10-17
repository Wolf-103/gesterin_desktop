package com.orozco.gesterin.service.Implement;

import com.orozco.gesterin.DAO.Implementaciones.UsuarioDAO;
import com.orozco.gesterin.model.Usuario;
import com.orozco.gesterin.service.AutenticateService;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 5 oct. 2024
 * @description Sistema GESTERIN
 */
public class AuhtenticateServiceImpl implements AutenticateService{

    private final UsuarioDAO usuarioDAO;

    public AuhtenticateServiceImpl() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Encriptar contraseña
     * @param password
     * @return 
     */
    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Validación de contraseña, utilizando métodos de la librería BCypt
     *
     * @param originalPassword: tipo String contraseña enviada por usuario
     * @param storedPasswordHash: tipo String hash o contraseña encriptada
     * @return boolean 
     */
    @Override
    public boolean validatePassword(String originalPassword, String storedPasswordHash) {
        return BCrypt.checkpw(originalPassword, storedPasswordHash);
    }

    /**
     * Validación de existencia de usaurio y comprobación de contraseña
     * @param nombre
     * @param contrasena
     * @return 
     */
    @Override
    public boolean authenticate(String nombre, String contrasena) {
        Usuario usuario = this.usuarioDAO.findByUsername(nombre);
        return usuario != null && validatePassword(contrasena, usuario.getConstrasena());
    }
}
