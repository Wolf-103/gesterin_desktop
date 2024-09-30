package com.orozco.gesterin.model;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 30 sep. 2024
 * @description Sistema GESTERIN
 */
public class Lesion {

    private Long id;
    private String nombre;
    private String extremidad;
    private String descripcion;

    public Lesion() {
    }

    public Lesion(String nombre, String extremidad, String descripcion) {
        this.nombre = nombre;
        this.extremidad = extremidad;
        this.descripcion = descripcion;
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

    public String getExtremidad() {
        return extremidad;
    }

    public void setExtremidad(String extremidad) {
        this.extremidad = extremidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
