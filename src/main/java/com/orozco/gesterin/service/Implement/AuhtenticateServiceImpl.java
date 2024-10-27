package com.orozco.gesterin.service.Implement;

import com.orozco.gesterin.DAO.Implementaciones.UsuarioDAO;
import com.orozco.gesterin.model.Usuario;
import com.orozco.gesterin.service.AutenticateService;
import com.orozco.gesterin.utils.BcryptUtil;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 5 oct. 2024
 * @description Sistema GESTERIN
 */
public class AuhtenticateServiceImpl implements AutenticateService {

    private final UsuarioDAO usuarioDAO;

    public AuhtenticateServiceImpl() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Validación de existencia de usaurio y comprobación de contraseña
     *
     * @param nombre
     * @param contrasena
     * @return
     */
    @Override
    public Usuario authenticate(String nombre, String contrasena) {
        Usuario usuario = this.usuarioDAO.findByUsername(nombre);
        if (usuario != null && BcryptUtil.validatePassword(contrasena, usuario.getConstrasena())) {
            return usuario;
        }
        return null;
    }
}
