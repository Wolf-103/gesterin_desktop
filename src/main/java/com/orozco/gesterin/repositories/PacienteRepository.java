/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.orozco.gesterin.repositories;

import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class PacienteRepository {

    private final ConnectionMysql connection;

    public PacienteRepository() {
        this.connection = new ConnectionMysql();
    }

    public boolean registrar(Paciente paciente) {
        try {
            String registarSQL = "INSERT INTO pacientes(firstname, lastname, dni, social_security, email, address, telephone) "
                    + " VALUES(?,?,?,?,?,?,?)";

            try (Connection conn = this.connection.getConn(); PreparedStatement sentencia = conn.prepareStatement(registarSQL)) {
                sentencia.setString(1, paciente.getFirstName());
                sentencia.setString(2, paciente.getLastName());
                sentencia.setString(3, paciente.getDni());
                sentencia.setString(4, paciente.getSocialSecurity());
                sentencia.setString(5, paciente.getEmail());
                sentencia.setString(6, paciente.getAddress());
                sentencia.setString(7, paciente.getTelephone());

                sentencia.executeUpdate();
            }

        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al registrar paciente");
            return false;
        }

        return true;
    }

}