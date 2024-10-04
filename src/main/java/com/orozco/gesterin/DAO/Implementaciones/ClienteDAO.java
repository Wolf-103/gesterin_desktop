package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.ConnectionMysql;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(registarSQL, Statement.RETURN_GENERATED_KEYS)) {
                this.setSentenceClient(sentence, cliente);

                if (sentence.executeUpdate() == 0) {
                    throw new SQLException("No se ha modificadado ninguna fila.");
                }

                try (ResultSet generatedKeys = sentence.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("No fue posible obtener el ID generado.");
                    }
                }
            }

        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al registrar cliente DNI: " + cliente.getDni());
            return false;
        }

        return true;
    }

    public List<Cliente> getAll() {
        List<Cliente> listClientes = new ArrayList<>();
        String request = "SELECT * FROM clientes";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request); ResultSet resultSet = sentence.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = this.cearCliente(resultSet);
                listClientes.add(cliente);
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar todos los clientes registrados");
        }
        return listClientes;
    }

    public boolean update(Cliente cliente) {
        String updateSQL = "UPDATE clientes SET firstname=?, lastname=?, dni=?, social_security=?, email=?, address=?, telephone=?, status=? WHERE id=?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            this.setSentenceClient(sentence, cliente);
            sentence.setLong(9, cliente.getId());

            sentence.executeUpdate();
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar cliente ID: " + cliente.getId());
            return false;
        }
        return true;
    }

    private void setSentenceClient(final PreparedStatement sentence, Cliente cliente) throws SQLException {
        sentence.setString(1, cliente.getFirstName());
        sentence.setString(2, cliente.getLastName());
        sentence.setString(3, cliente.getDni());
        sentence.setString(4, cliente.getSocialSecurity());
        sentence.setString(5, cliente.getEmail());
        sentence.setString(6, cliente.getAddress());
        sentence.setString(7, cliente.getTelephone());
        sentence.setBoolean(8, cliente.getStatus());
    }

    public List<Cliente> buscarClientePorParametros(String parametro) {
        List<Cliente> listClientes = new ArrayList<>();
        String request = "SELECT * FROM clientes WHERE dni LIKE ? OR nombre LIKE ? OR apellido LIKE ?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
            String querySQL = "%" + parametro + "%";
            sentence.setString(1, querySQL);
            sentence.setString(2, querySQL);
            sentence.setString(3, querySQL);

            try (ResultSet resultSet = sentence.executeQuery()) {
                while (resultSet.next()) {
                    Cliente cliente = cearCliente(resultSet);
                    listClientes.add(cliente);
                }
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar clientes por parametro: " + parametro);
        }
        return listClientes;
    }

    private Cliente cearCliente(final ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getLong(1));
        cliente.setDni(resultSet.getString(2));
        cliente.setEmail(resultSet.getString(3));
        cliente.setSocialSecurity(resultSet.getString(4));
        cliente.setTelephone(resultSet.getString(5));
        cliente.setFirstName(resultSet.getString(6));
        cliente.setLastName(resultSet.getString(7));
        cliente.setStatus(resultSet.getBoolean(8));
        cliente.setAddress(resultSet.getString(9));
        return cliente;
    }
}
