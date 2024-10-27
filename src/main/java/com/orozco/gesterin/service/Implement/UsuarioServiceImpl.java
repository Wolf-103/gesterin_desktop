package com.orozco.gesterin.service.Implement;

import com.orozco.gesterin.DAO.Implementaciones.UsuarioDAO;
import com.orozco.gesterin.model.Persona;
import com.orozco.gesterin.model.Usuario;
import com.orozco.gesterin.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 5 oct. 2024
 * @description Sistema GESTERIN
 */
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;
//    private final PersonaDAO personaDAO;
//    private final AdministradorDAO administradorDAO;
//    private final ProfesionalDAO profesionalDAO;

    public UsuarioServiceImpl() {
        this.usuarioDAO = new UsuarioDAO();
//        this.personaDAO = new PersonaDAO();
//        this.administradorDAO = new AdministradorDAO(this.personaDAO);
//        this.profesionalDAO = new ProfesionalDAO(this.personaDAO);
    }

    @Override
    public List<Persona> findPersonaUsuario(Boolean estado) {
        List<Persona> listaPersonaUsuario = new ArrayList<>();

        return listaPersonaUsuario;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return this.usuarioDAO.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return this.usuarioDAO.findAll();
    }

    @Override
    public List<Usuario> findByParams(String params) {
        return this.usuarioDAO.findAllByParams(params);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return this.usuarioDAO.update(usuario);
    }

    @Override
    public boolean delete(Long idUsuario) {
        return this.usuarioDAO.delete(idUsuario);
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return this.usuarioDAO;
    }

    /**
     * Verifica si existe el nombre de usuario
     *
     * @param username: STring nombre del usuario
     * @return boolean true si lo encuentra false si no
     */
    @Override
    public boolean existByUsername(String username) {
        return this.usuarioDAO.existByUsername(username);
    }
}
