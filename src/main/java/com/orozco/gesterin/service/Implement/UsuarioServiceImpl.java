package com.orozco.gesterin.service.Implement;

import com.orozco.gesterin.DAO.Implementaciones.AdministradorDAO;
import com.orozco.gesterin.DAO.Implementaciones.PersonaDAO;
import com.orozco.gesterin.DAO.Implementaciones.ProfesionalDAO;
import com.orozco.gesterin.DAO.Implementaciones.UsuarioDAO;
import com.orozco.gesterin.model.Persona;
import com.orozco.gesterin.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 5 oct. 2024
 * @description Sistema GESTERIN
 */
public class UsuarioServiceImpl {

    private final UsuarioDAO usuarioDAO;
    private final PersonaDAO personaDAO;
    private final AdministradorDAO administradorDAO;
    private final ProfesionalDAO profesionalDAO;

    public UsuarioServiceImpl() {
        this.usuarioDAO = new UsuarioDAO();
        this.personaDAO = new PersonaDAO();
        this.administradorDAO = new AdministradorDAO(this.personaDAO);
        this.profesionalDAO = new ProfesionalDAO(this.personaDAO);
    }

    public List<Persona> findPersonaUsuario(Boolean estado) {
        List<Persona> listaPersonaUsuario = new ArrayList<>();

        return listaPersonaUsuario;
    }

    public Usuario save(Usuario usuario) {
        return this.usuarioDAO.save(usuario);
    }

    public List<Usuario> findAll() {
        return this.usuarioDAO.findAll();
    }

    public List<Usuario> findByParams(String params) {
        return this.usuarioDAO.findAllByParams(params);
    }

    public Usuario update(Usuario usuario) {
        return this.usuarioDAO.update(usuario);
    }

    public boolean delete(Long idUsuario) {
        return this.usuarioDAO.delete(idUsuario);
    }
}
