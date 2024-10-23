package com.orozco.gesterin.service;

import com.orozco.gesterin.model.Persona;
import com.orozco.gesterin.model.Usuario;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 22 oct. 2024
 * @description Sistema GESTERIN
 */
public interface UsuarioService {

    List<Persona> findPersonaUsuario(Boolean estado);

    Usuario save(Usuario usuario);

    List<Usuario> findAll();

    List<Usuario> findByParams(String params);

    Usuario update(Usuario usuario);

    boolean delete(Long idUsuario);

}
