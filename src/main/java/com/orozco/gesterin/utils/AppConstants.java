/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.orozco.gesterin.utils;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 18 sep. 2024
 * @description Sistema GESTERIN
 */
public class AppConstants {

    public static final int NAME_LASTNAME_MIN = 3;
    public static final int NAME_LASTNAME_MAX = 20;
    public static final int MAX_LOGIN_ATTEMPTS = 5;
    public static final long BLOCK_DURATION = 1 * 60 * 1000; // 5 MIN
    public static final int DNI_MAX = 8;
    public static final int DNI_MIN = 7;
    public static final int RAZON_SOCIAL_MIN = 5;
    public static final int RAZON_SOCIAL_MAX = 120;
    public static final int CUIT_CUIL_MAX = 8 + 3;
    public static final int CUIT_CUIL_MIN = 7 + 3;
    public static final int ADDRESS_MIN = 3;
    public static final int ADDRESS_MAX = 1200;
    public static final int TELEPHONE_MAX = 12;
    public static final int TELEPHONE_MIN = 10;
    public static final int MOVILPHONE_MAX = 12;
    public static final int MOVILPHONE_MIN = 10;
    public static final int PASSWORD_MAX = 30;
    public static final int PASSWORD_SIZE_DEF = 12;
    public static final int PASSWORD_MIN = 8;
    public static final int DESCRIPTION_MAX_DEF = 255;
    public static final int DESCRIPTION_MIN = 0;
    public static final int DESCRIPTION_MAX = 1200;
    public static final int OBSERVATION_MAX_DEF = 255;
    public static final int OBSERVATION_MIN = 3;
    public static final int OBSERVATION_MAX = 2000;

    public static final String PATTERN_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final String PATTERN_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[A-Za-z0-9]{" + PASSWORD_MIN + ",}$";
    public static final String PATTERN_ONLY_NUMBER = "^[0-9]$";
    public static final String PATTERN_DNI = "^[0-9]{" + DNI_MIN + "," + DNI_MAX + "}$";
    public static final String PATTERN_CUIT_CUIL = "^[0-9]{" + CUIT_CUIL_MIN + "," + CUIT_CUIL_MAX + "}$";
    public static final String PATTERN_TELEPHONE = "^[0-9]{" + TELEPHONE_MIN + "," + TELEPHONE_MAX + "}$";
    public static final String PATTERN_MOVILPHONE = "^[0-9]{" + MOVILPHONE_MIN + "," + MOVILPHONE_MAX + "}$";
    
    public static final String PATTERN_NAME_LASTNAME_SLIM = "^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]+$";
    public static final String PATTERN_NAME_LASTNAME = "^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]{" + NAME_LASTNAME_MIN + "," + NAME_LASTNAME_MAX + "}$";
    public static final String PATTERN_DESCRIPTION_DEF = "^[\\p{L}\\p{Nd}\\s.,;:()¿?¡!_-]{" + 0 + "," + DESCRIPTION_MAX_DEF + "}$";
    public static final String PATTERN_DESCRIPTION = "^[\\p{L}\\p{Nd}\\s.,;:()¿?¡!_-]{" + DESCRIPTION_MIN + "," + DESCRIPTION_MAX + "}$";
    public static final String PATTERN_ADDRESS = "^[\\p{L}\\p{Nd}\\s.,_-]{" + ADDRESS_MIN + "," + ADDRESS_MAX + "}$";
    public static final String PATTERN_RAZON_SOCIAL = "^[\\p{L}\\p{Nd}\\s.,_-]{" + RAZON_SOCIAL_MIN + "," + RAZON_SOCIAL_MAX + "}$";

}
