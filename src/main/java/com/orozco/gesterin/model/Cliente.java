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
    private String firstName;
    private String lastName;
    private String dni;
    private String socialSecurity;
    private String email;
    private Boolean status;
    private String address;
    private String telephone;
    private CaracteristicasFisicas caracteristicasFisicas;

    public Cliente() {
    }

    public Cliente(String firstName, String lastName, String dni, String socialSecurity, String email, Boolean status, String address, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.socialSecurity = socialSecurity;
        this.email = email;
        this.status = status;
        this.address = address;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public CaracteristicasFisicas getCaracteristicasFisicas() {
        return caracteristicasFisicas;
    }

    public void setCaracteristicasFisicas(CaracteristicasFisicas caracteristicasFisicas) {
        this.caracteristicasFisicas = caracteristicasFisicas;
    }

}
