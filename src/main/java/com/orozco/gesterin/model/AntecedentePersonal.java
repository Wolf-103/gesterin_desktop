package com.orozco.gesterin.model;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 30 sep. 2024
 * @description Sistema GESTERIN
 */
public class AntecedentePersonal {

    private Long id;
    private String observacion;
    private TipoAntecedente tipoAntecedente;
    private Long cliente_id;

    public AntecedentePersonal() {
    }

    public AntecedentePersonal(String observacion, TipoAntecedente tipoAntecedente) {
        this.observacion = observacion;
        this.tipoAntecedente = tipoAntecedente;
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

    public TipoAntecedente getTipoAntecedente() {
        return tipoAntecedente;
    }

    public void setTipoAntecedente(TipoAntecedente tipoAntecedente) {
        this.tipoAntecedente = tipoAntecedente;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

}
