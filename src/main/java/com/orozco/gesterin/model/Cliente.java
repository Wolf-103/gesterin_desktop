package com.orozco.gesterin.model;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class Cliente extends Persona {

    private String dni;
    private String obraSocial;
    private Boolean estado;
    private String direccion;
    private CaracteristicasFisicas caracteristicasFisicas;

    public Cliente(String dni, String obraSocial, Boolean estado, String direccion, String nombre, String apellido, String email, String telefono) {
        super(nombre, apellido, email, telefono);
        this.dni = dni;
        this.obraSocial = obraSocial;
        this.estado = estado;
        this.direccion = direccion;
    }

    public Cliente() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public CaracteristicasFisicas getCaracteristicasFisicas() {
        return caracteristicasFisicas;
    }

    public void setCaracteristicasFisicas(CaracteristicasFisicas caracteristicasFisicas) {
        this.caracteristicasFisicas = caracteristicasFisicas;
    }

}
