package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.ConnectionMysql;
import com.orozco.gesterin.DAO.GenericDAO;
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
public class ClienteDAO implements GenericDAO<Cliente, Long> {

    private final ConnectionMysql connection;

    public ClienteDAO() {
        this.connection = new ConnectionMysql();
    }

    @Override
    public Cliente save(Cliente entity) {
        try {
            String registarSQL = "INSERT INTO clientes(nombre, apellido, dni, obra_social, email, direccion, telefono, estado) "
                    + " VALUES(?,?,?,?,?,?,?,?)";

            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(registarSQL, Statement.RETURN_GENERATED_KEYS)) {
                this.setSentenceEntity(sentence, entity);

                if (sentence.executeUpdate() == 0) {
                    throw new SQLException("No se ha modificadado ninguna fila.");
                }

                try (ResultSet generatedKeys = sentence.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("No fue posible obtener el ID generado.");
                    }
                }
            }

        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al registrar cliente DNI: " + entity.getDni());
            return null;
        }

        return this.findById(entity.getId());
    }

    @Override
    public Cliente findById(Long id) {
        String findSQL = "SELECT * FROM clientes WHERE id=?";
        Cliente entity = null;
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                if (rs.next()) {
                    entity = this.cearEntity(rs);
                }
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar cliente por ID: " + id);
        }
        return entity;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> listClientes = new ArrayList<>();
        String request = "SELECT * FROM clientes";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request); ResultSet resultSet = sentence.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = this.cearEntity(resultSet);
                listClientes.add(cliente);
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar todos los clientes registrados");
        }
        return listClientes;
    }

    @Override
    public Cliente update(Cliente entity) {
        String updateSQL = "UPDATE clientes SET nombre=?, apellido=?, dni=?, obra_social=?, email=?, direccion=?, telefono=?, estado=? WHERE id=?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            this.setSentenceEntity(sentence, entity);
            sentence.setLong(9, entity.getId());
            if (sentence.executeUpdate() == 0) {
                throw new SQLException("No se ha modificadado ninguna fila.");
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar cliente ID: " + entity.getId());
            return null;
        }
        return this.findById(entity.getId());
    }

    public List<Cliente> findAllByParams(String parametro) {
        List<Cliente> listClientes = new ArrayList<>();
        String request = "SELECT * FROM clientes WHERE dni LIKE ? OR nombre LIKE ? OR apellido LIKE ?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
            String querySQL = "%" + parametro + "%";
            sentence.setString(1, querySQL);
            sentence.setString(2, querySQL);
            sentence.setString(3, querySQL);

            try (ResultSet resultSet = sentence.executeQuery()) {
                while (resultSet.next()) {
                    Cliente cliente = cearEntity(resultSet);
                    listClientes.add(cliente);
                }
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar clientes por parametro: " + parametro);
        }
        return listClientes;
    }

    @Override
    public boolean delete(Long id) {
        String deleteSQL = "DELETE FROM clientes WHERE id=?";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(deleteSQL)) {
            sentence.setLong(1, id);
            
            if (sentence.executeUpdate() == 0) {
                throw new SQLException("No se ha modificadado ninguna fila.");
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al eliminar cliente ID: " + id);
            return false;
        }
        return true;
    }

    private void setSentenceEntity(final PreparedStatement sentence, Cliente entity) throws SQLException {
        sentence.setString(1, entity.getNombre());
        sentence.setString(2, entity.getApellido());
        sentence.setString(3, entity.getDni());
        sentence.setString(4, entity.getObraSocial());
        sentence.setString(5, entity.getEmail());
        sentence.setString(6, entity.getDireccion());
        sentence.setString(7, entity.getTelefono());
        sentence.setBoolean(8, entity.getEstado());
    }

    private Cliente cearEntity(final ResultSet resultSet) throws SQLException {
        Cliente entity = new Cliente();
        entity.setId(resultSet.getLong("id"));
        entity.setDni(resultSet.getString("dni"));
        entity.setEmail(resultSet.getString("email"));
        entity.setObraSocial(resultSet.getString("obra_social"));
        entity.setTelefono(resultSet.getString("telefono"));
        entity.setNombre(resultSet.getString("nombre"));
        entity.setApellido(resultSet.getString("apellido"));
        entity.setEstado(resultSet.getBoolean("estado"));
        entity.setDireccion(resultSet.getString("direccion"));
        return entity;
    }

}
