package com.orozco.gesterin.model;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class Cliente {

    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String obraSocial;
    private String email;
    private Boolean estado;
    private String direccion;
    private String telefono;
    private CaracteristicasFisicas caracteristicasFisicas;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String dni, String obraSocial, String email, Boolean estado, String direccion, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.obraSocial = obraSocial;
        this.email = email;
        this.estado = estado;
        this.direccion = direccion;
        this.telefono = telefono;
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

    public void setNombre(String firstName) {
        this.nombre = firstName;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String lastName) {
        this.apellido = lastName;
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

    public void setObraSocial(String socialSecurity) {
        this.obraSocial = socialSecurity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean status) {
        this.estado = status;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String address) {
        this.direccion = address;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telephone) {
        this.telefono = telephone;
    }

    public CaracteristicasFisicas getCaracteristicasFisicas() {
        return caracteristicasFisicas;
    }

    public void setCaracteristicasFisicas(CaracteristicasFisicas caracteristicasFisicas) {
        this.caracteristicasFisicas = caracteristicasFisicas;
    }

}
