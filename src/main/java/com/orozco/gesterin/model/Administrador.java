package com.orozco.gesterin.model;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class Administrador extends Persona {

    private Usuario usuario;

    public Administrador() {
    }

    public Administrador(Usuario usuario, String nombre, String apellido, String email, String telefono) {
        super(nombre, apellido, email, telefono);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
