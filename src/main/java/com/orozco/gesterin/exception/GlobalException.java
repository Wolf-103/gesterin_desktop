package com.orozco.gesterin.exception;

import java.io.Serial;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 19 sep. 2024
 * @description Sistema GESTERIN
 */
public class GlobalException extends CustomeBaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    private int code;
    private String details;

    public GlobalException(int code, String details) {
        super();
        this.code = code;
        this.details = details;
    }

    public GlobalException(int code, String message, String details) {
        super(message);
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
