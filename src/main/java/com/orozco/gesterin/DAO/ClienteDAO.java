package com.orozco.gesterin.DAO;

import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.Cliente;
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
public class ClienteDAO {

    private final ConnectionMysql connection;

    public ClienteDAO() {
        this.connection = new ConnectionMysql();
    }

    public boolean registrar(Cliente cliente) {
        try {
            String registarSQL = "INSERT INTO clientes(firstname, lastname, dni, social_security, email, address, telephone, status) "
                    + " VALUES(?,?,?,?,?,?,?,?)";

            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(registarSQL)) {
                sentence.setString(1, cliente.getFirstName());
                sentence.setString(2, cliente.getLastName());
                sentence.setString(3, cliente.getDni());
                sentence.setString(4, cliente.getSocialSecurity());
                sentence.setString(5, cliente.getEmail());
                sentence.setString(6, cliente.getAddress());
                sentence.setString(7, cliente.getTelephone());
                sentence.setString(8, cliente.getStatus());

                sentence.executeUpdate();
            }

        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al registrar cliente");
            return false;
        }

        return true;
    }

    public List<Cliente> getAll() {
        List<Cliente> listClientes = new ArrayList<>();
        try {
            String request = "SELECT * FROM clientes";
            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
                ResultSet resultSet = sentence.executeQuery();
                while (resultSet.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(resultSet.getLong(1));
                    cliente.setDni(resultSet.getString(2));
                    cliente.setEmail(resultSet.getString(3));
                    cliente.setSocialSecurity(resultSet.getString(4));
                    cliente.setTelephone(resultSet.getString(5));
                    cliente.setFirstName(resultSet.getString(6));
                    cliente.setLastName(resultSet.getString(7));
                    cliente.setStatus(resultSet.getString(8));
                    cliente.setAddress(resultSet.getString(9));
                    listClientes.add(cliente);
                }
            }

        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar cliente registrados");
        }
        return listClientes;
    }

    public boolean update(Cliente cliente) {
        try {
            String updateSQL = "UPDATE clientes SET firstname=?, lastname=?, dni=?,"
                    + " social_security=?, email=?, address=?, telephone=?, status=? "
                    + " WHERE id=?";

            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
                sentence.setString(1, cliente.getFirstName());
                sentence.setString(2, cliente.getLastName());
                sentence.setString(3, cliente.getDni());
                sentence.setString(4, cliente.getSocialSecurity());
                sentence.setString(5, cliente.getEmail());
                sentence.setString(6, cliente.getAddress());
                sentence.setString(7, cliente.getTelephone());
                sentence.setString(8, cliente.getStatus());

                sentence.setLong(9, cliente.getId());

                sentence.executeUpdate();
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar cliente");
            return false;
        }
        return true;
    }
}
