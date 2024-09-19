package com.orozco.gesterin.repositories;

import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            String registarSQL = "INSERT INTO pacientes(firstname, lastname, dni, social_security, email, address, telephone, status) "
                    + " VALUES(?,?,?,?,?,?,?,?)";

            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(registarSQL)) {
                sentence.setString(1, paciente.getFirstName());
                sentence.setString(2, paciente.getLastName());
                sentence.setString(3, paciente.getDni());
                sentence.setString(4, paciente.getSocialSecurity());
                sentence.setString(5, paciente.getEmail());
                sentence.setString(6, paciente.getAddress());
                sentence.setString(7, paciente.getTelephone());
                sentence.setString(8, paciente.getStatus());

                sentence.executeUpdate();
            }

        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al registrar paciente");
            return false;
        }

        return true;
    }

    public List<Paciente> getAll() {
        List<Paciente> listPaciente = new ArrayList<>();
        try {
            String request = "SELECT * FROM pacientes";
            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
                ResultSet resultSet = sentence.executeQuery();
                while (resultSet.next()) {
                    Paciente paciente = new Paciente();
                    paciente.setId(resultSet.getLong(1));
                    paciente.setDni(resultSet.getString(2));
                    paciente.setEmail(resultSet.getString(3));
                    paciente.setSocialSecurity(resultSet.getString(4));
                    paciente.setTelephone(resultSet.getString(5));
                    paciente.setFirstName(resultSet.getString(6));
                    paciente.setLastName(resultSet.getString(7));
                    paciente.setStatus(resultSet.getString(8));
                    paciente.setAddress(resultSet.getString(9));
                    listPaciente.add(paciente);
                }
            }

        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar pacientes registrados");
        }
        return listPaciente;
    }

    public boolean update(Paciente paciente) {
        try {
            String updateSQL = "UPDATE pacientes SET firstname=?, lastname=?, dni=?,"
                    + " social_security=?, email=?, address=?, telephone=?, status=? "
                    + " WHERE id=?";

            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
                sentence.setString(1, paciente.getFirstName());
                sentence.setString(2, paciente.getLastName());
                sentence.setString(3, paciente.getDni());
                sentence.setString(4, paciente.getSocialSecurity());
                sentence.setString(5, paciente.getEmail());
                sentence.setString(6, paciente.getAddress());
                sentence.setString(7, paciente.getTelephone());
                sentence.setString(8, paciente.getStatus());

                sentence.setLong(9, paciente.getId());

                sentence.executeUpdate();
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar paciente");
            return false;
        }
        return true;
    }
}
