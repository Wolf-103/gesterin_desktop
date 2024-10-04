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
public class CaracteristicasFisicas {

    private Long id;
    private Float altura;
    private Float peso;
    private Float tensionArterialBI;
    private Float tensionArterialBD;
    private Long cliente_id;

    public CaracteristicasFisicas() {
    }

    public CaracteristicasFisicas(Float altura, Float peso, Float tensionArterialBI, Float tensionArterialBD, Long cliente_id) {
        this.altura = altura;
        this.peso = peso;
        this.tensionArterialBI = tensionArterialBI;
        this.tensionArterialBD = tensionArterialBD;
        this.cliente_id = cliente_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getTensionArterialBI() {
        return tensionArterialBI;
    }

    public void setTensionArterialBI(Float tensionArterialBI) {
        this.tensionArterialBI = tensionArterialBI;
    }

    public Float getTensionArterialBD() {
        return tensionArterialBD;
    }

    public void setTensionArterialBD(Float tensionArterialBD) {
        this.tensionArterialBD = tensionArterialBD;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

}
