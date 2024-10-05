package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.ConnectionMysql;
import com.orozco.gesterin.DAO.GenericDAO;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.Profesional;
import com.orozco.gesterin.model.Usuario;
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
public class ProfesionalDAO implements GenericDAO<Profesional, Long> {

    private final ConnectionMysql connection;

    public ProfesionalDAO() {
        this.connection = new ConnectionMysql();
    }

    @Override
    public boolean save(Profesional entity) {
        try {
            String registarSQL = "INSERT INTO profesionales(nombre, apellido, email, telefono, estado, usuario_id) "
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
            ControllerExceptionHandler.handleError(ex, "Error al registrar profesional email: " + entity.getEmail());
            return false;
        }

        return true;
    }

    @Override
    public Profesional findById(Long id) {
        String findSQL = "SELECT * FROM profesionales WHERE id=?";
        Profesional entity = null;
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                if (rs.next()) {
                    entity = this.cearEntity(rs);
                }
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar profesional por ID: " + id);
        }
        return entity;
    }

    @Override
    public List<Profesional> findAll() {
        List<Profesional> listaEntities = new ArrayList<>();
        String request = "SELECT * FROM profesionales";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request); ResultSet resultSet = sentence.executeQuery()) {

            while (resultSet.next()) {
                Profesional entity = this.cearEntity(resultSet);
                listaEntities.add(entity);
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar todos los profesionales registrados");
        }
        return listaEntities;
    }

    @Override
    public boolean update(Profesional entity) {
        String updateSQL = "UPDATE profesionales SET nombre=?, apellido=?, email=?, telefono=?, estado=?, usuario_id=? WHERE id=?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            this.setSentenceEntity(sentence, entity);
            sentence.setLong(6, entity.getId());

            sentence.executeUpdate();
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar profesional ID: " + entity.getId());
            return false;
        }
        return true;
    }

    public List<Profesional> findAllByParams(String parametro) {
        List<Profesional> listEntity = new ArrayList<>();
        String request = "SELECT * FROM profesionales WHERE nombre LIKE ? OR apellido LIKE ? OR email LIKE ?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
            String querySQL = "%" + parametro + "%";
            sentence.setString(1, querySQL);
            sentence.setString(2, querySQL);
            sentence.setString(3, querySQL);

            try (ResultSet resultSet = sentence.executeQuery()) {
                while (resultSet.next()) {
                    Profesional entity = cearEntity(resultSet);
                    listEntity.add(entity);
                }
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar profesional por parametro: " + parametro);
        }
        return listEntity;
    }

    @Override
    public boolean delete(Long id) {
        String deleteSQL = "DELETE FROM profesiones WHERE id=?";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(deleteSQL)) {
            sentence.setLong(1, id);
            sentence.executeUpdate();
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al eliminar profesional ID: " + id);
            return false;
        }
        return true;
    }

    private void setSentenceEntity(final PreparedStatement sentence, Profesional entity) throws SQLException {
        sentence.setString(1, entity.getNombre());
        sentence.setString(2, entity.getApellido());
        sentence.setString(3, entity.getEmail());
        sentence.setString(4, entity.getTelefono());
        sentence.setLong(5, entity.getUsuario().getId());
    }

    private Profesional cearEntity(final ResultSet resultSet) throws SQLException {
        Profesional entity = new Profesional();
        entity.setId(resultSet.getLong(1));
        entity.setEmail(resultSet.getString(2));
        entity.setTelefono(resultSet.getString(3));
        entity.setNombre(resultSet.getString(4));
        entity.setApellido(resultSet.getString(5));
        Usuario us = new Usuario();
        us.setId(resultSet.getLong(6));
        entity.setUsuario(us);
        return entity;
    }

}
