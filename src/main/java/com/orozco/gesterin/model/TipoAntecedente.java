package com.orozco.gesterin.model;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class TipoAntecedente extends TipoBase {

    public TipoAntecedente(Long id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }

    public TipoAntecedente(String nombre, String descripcion) {
        super(nombre, descripcion);
    }

}
