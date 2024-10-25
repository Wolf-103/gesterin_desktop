package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.CaracteristicasFisicas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class CaracteristicasFisicasDAO {

    private final ConnectionMysqlImpl connection;

    public CaracteristicasFisicasDAO() {
        this.connection = new ConnectionMysqlImpl();
    }

    public boolean registrar(CaracteristicasFisicas caracteristicas) {
        try {
            String registarSQL = "INSERT INTO caracteristicas_fisicas(altura, peso, tensionArterialBI, tensionArterialBD, cliente_id)"
                    + " VALUES (?, ?, ?, ?, ?) ";

            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(registarSQL, Statement.RETURN_GENERATED_KEYS)) {
                sentence.setFloat(1, caracteristicas.getAltura());
                sentence.setFloat(2, caracteristicas.getPeso());
                sentence.setFloat(3, caracteristicas.getTensionArterialBI());
                sentence.setFloat(4, caracteristicas.getTensionArterialBD());
                sentence.setLong(5, caracteristicas.getCliente_id());

                if (sentence.executeUpdate() == 0) {
                    throw new SQLException("No se ha modificadado ninguna fila.");
                }

                try (ResultSet generatedKeys = sentence.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        caracteristicas.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("No fue posible obtener el ID generado.");
                    }
                }
            }

        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al registrar Caracteristicas Físicas del Cliente ID: " + caracteristicas.getCliente_id());
            return false;
        }

        return true;
    }

    public CaracteristicasFisicas getByClienteId(Long clienteId) {
        CaracteristicasFisicas caracteristicas = null;
        String request = "SELECT * FROM caracteristicas_fisicas WHERE cliente_id=?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
            sentence.setLong(1, clienteId);

            try (ResultSet resultSet = sentence.executeQuery()) {
                if (resultSet.next()) {
                    caracteristicas = new CaracteristicasFisicas();
                    caracteristicas.setId(resultSet.getLong("id"));
                    caracteristicas.setAltura(resultSet.getFloat("altura"));
                    caracteristicas.setPeso(resultSet.getFloat("peso"));
                    caracteristicas.setTensionArterialBI(resultSet.getFloat("tensionArterialBI"));
                    caracteristicas.setTensionArterialBD(resultSet.getFloat("tensionArterialBD"));
                    caracteristicas.setCliente_id(resultSet.getLong("cliente_id"));
                }
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar Caracteristicas Físicas del Cliente ID: " + clienteId);
        }

        return caracteristicas;
    }

    public boolean update(CaracteristicasFisicas caracteristicas) {
        String updateSQL = "UPDATE caracteristicas_fisicas SET altura=?, peso=?, tensionArterialBI=?, tensionArterialBD=?, cliente_id=? WHERE id=?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            sentence.setFloat(1, caracteristicas.getAltura());
            sentence.setFloat(2, caracteristicas.getPeso());
            sentence.setFloat(3, caracteristicas.getTensionArterialBI());
            sentence.setFloat(4, caracteristicas.getTensionArterialBD());
            sentence.setLong(5, caracteristicas.getCliente_id());
            sentence.setLong(6, caracteristicas.getId());

            if (sentence.executeUpdate() == 0) {
                throw new SQLException("No se ha actualizado ninguna fila.");
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar Caracteristicas Físicas del Cliente ID: " + caracteristicas.getCliente_id());
            return false;
        }

        return true;
    }
}
