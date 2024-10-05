/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.orozco.gesterin.model;

/**
 *
 * @author crist
 */
public class AntecedentePersonal {

    private Long id;
    private String observacion;
    private Long tipoAntecedenteId;
    private Long clienteId;

    public AntecedentePersonal() {
    }

    public AntecedentePersonal(String observacion, Long tipoAntecedenteId, Long clienteId) {
        this.observacion = observacion;
        this.tipoAntecedenteId = tipoAntecedenteId;
        this.clienteId = clienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Long getTipoAntecedenteId() {
        return tipoAntecedenteId;
    }

    public void setTipoAntecedenteId(Long tipoAntecedenteId) {
        this.tipoAntecedenteId = tipoAntecedenteId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

}
