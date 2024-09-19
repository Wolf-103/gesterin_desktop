/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.orozco.gesterin.exception;

import java.io.Serial;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 19 sep. 2024
 * @description Sistema GESTERIN
 */
public class FieldEmptyException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private int code;
    private String details;

    public FieldEmptyException(int code, String details) {
        this.code = code;
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public String getDetails() {
        return details;
    }

}
