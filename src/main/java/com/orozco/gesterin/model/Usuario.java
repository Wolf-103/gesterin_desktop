package com.orozco.gesterin.model;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 18 sep. 2024
 * @description Sistema GESTERIN
 */
public class Usuario {

    private Long id;
    private String nombre;
    private String constrasena;
    private Boolean estado;
    private Long rol_id;

    public Usuario() {
    }

    public Usuario(String nombre, String constrasena, Boolean estado, Long rol_id) {
        this.nombre = nombre;
        this.constrasena = constrasena;
        this.estado = estado;
        this.rol_id = rol_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getConstrasena() {
        return constrasena;
    }

    public void setConstrasena(String constrasena) {
        this.constrasena = constrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Long getRol_id() {
        return rol_id;
    }

    public void setRol_id(Long rol_id) {
        this.rol_id = rol_id;
    }

}
