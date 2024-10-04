/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.orozco.gesterin.model;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class Rol extends TipoBase {

    public Rol(Long id, String nombre, String descripcion) {
        super(id, nombre, descripcion);
    }

    public Rol(String nombre, String descripcion) {
        super(nombre, descripcion);
    }

}
