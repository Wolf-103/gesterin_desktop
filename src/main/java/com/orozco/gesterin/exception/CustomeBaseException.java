package com.orozco.gesterin.exception;

import java.io.Serial;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 19 sep. 2024
 * @description Sistema GESTERIN
 */
public class CustomeBaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CustomeBaseException(String message) {
        super(message);
    }

    public CustomeBaseException() {
        super();
    }
}
