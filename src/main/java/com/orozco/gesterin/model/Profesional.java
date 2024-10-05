package com.orozco.gesterin.model;

import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class Profesional extends Persona {

    private List<Especialidad> listaEspecialidades;
    private Usuario usuario;

    public Profesional() {
    }

    public Profesional(Usuario usuario, String nombre, String apellido, String email, String telefono) {
        super(nombre, apellido, email, telefono);
        this.usuario = usuario;
    }

    public List<Especialidad> getListaEspecialidades() {
        return listaEspecialidades;
    }

    public void setListaEspecialidades(List<Especialidad> listaEspecialidades) {
        this.listaEspecialidades = listaEspecialidades;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
