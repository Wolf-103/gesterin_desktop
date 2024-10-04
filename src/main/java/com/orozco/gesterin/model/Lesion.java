package com.orozco.gesterin.model;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class Lesion extends TipoBase {

    private String extremidad;

    public Lesion(String extremidad, String nombre, String descripcion) {
        super(nombre, descripcion);
        this.extremidad = extremidad;
    }

    public Lesion(Long id, String nombre, String descripcion, String extremiedad) {
        super(id, nombre, descripcion);
        this.extremidad = extremiedad;
    }

    public String getExtremidad() {
        return extremidad;
    }

    public void setExtremidad(String extremidad) {
        this.extremidad = extremidad;
    }

}
