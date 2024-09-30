package com.orozco.gesterin.model;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 30 sep. 2024
 * @description Sistema GESTERIN
 */
public class CaracteristicasFisicas {

    private Long id;
    private float altura;
    private float peso;
    private float tensionArterialBI;
    private float tensionArterialBD;

    public CaracteristicasFisicas() {
    }

    public CaracteristicasFisicas(float altura, float peso, float tensionArterialBI, float tensionArterialBD) {
        this.altura = altura;
        this.peso = peso;
        this.tensionArterialBI = tensionArterialBI;
        this.tensionArterialBD = tensionArterialBD;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getTensionArterialBI() {
        return tensionArterialBI;
    }

    public void setTensionArterialBI(float tensionArterialBI) {
        this.tensionArterialBI = tensionArterialBI;
    }

    public float getTensionArterialBD() {
        return tensionArterialBD;
    }

    public void setTensionArterialBD(float tensionArterialBD) {
        this.tensionArterialBD = tensionArterialBD;
    }

}
