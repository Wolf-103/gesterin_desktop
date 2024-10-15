package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.GenericDAO;
import com.orozco.gesterin.dto.PersonaDTO;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.Administrador;
import com.orozco.gesterin.model.Persona;
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
public class AdministradorDAO implements GenericDAO<Administrador, Long> {

    private final PersonaDAO personaDAO;

    public AdministradorDAO(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    @Override
    public Administrador save(Administrador entity) {
        try {
            Persona persona = this.personaDAO.save(new PersonaDTO(
                    entity.getNombre(), entity.getApellido(), entity.getEmail(), entity.getTelefono()));
            entity.setId(persona.getId());
            String registarSQL = "INSERT INTO administradores(id, estado, usuario_id) "
                    + " VALUES(?,?,?)";

            try (Connection conn = this.personaDAO.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(registarSQL, Statement.RETURN_GENERATED_KEYS)) {
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
            ControllerExceptionHandler.handleError(ex, "Error al registrar Administrador email: " + entity.getEmail());
            return null;
        }
        return this.findById(entity.getId());
    }

    @Override
    public Administrador findById(Long id) {
        String findSQL = "SELECT * FROM administradores WHERE id=?";
        Administrador entity = null;
        try (Connection conn = this.personaDAO.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                if (rs.next()) {
                    entity = this.cearEntity(rs);
                }
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar Administrador por ID: " + id);
        }
        return entity;
    }

    @Override
    public List<Administrador> findAll() {
        List<Administrador> listaEntities = new ArrayList<>();
        String request = "SELECT * FROM administradores";

        try (Connection conn = this.personaDAO.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request); ResultSet resultSet = sentence.executeQuery()) {

            while (resultSet.next()) {
                Administrador entity = this.cearEntity(resultSet);
                listaEntities.add(entity);
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar todos los Administrador registrados");
        }
        return listaEntities;
    }

    @Override
    public Administrador update(Administrador entity) {
        String updateSQL = "UPDATE administradores SET nombre=?, apellido=?, email=?, telefono=?, estado=?, usuario_id=? WHERE id=?";

        try (Connection conn = this.personaDAO.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            this.setSentenceEntity(sentence, entity);
            sentence.setLong(6, entity.getId());

            sentence.executeUpdate();
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar Administrador ID: " + entity.getId());
            return null;
        }
        return this.findById(entity.getId());
    }

    public List<Administrador> findAllByParams(String parametro) {
        List<Administrador> listEntity = new ArrayList<>();
        String request = "SELECT * FROM administradores WHERE nombre LIKE ? OR apellido LIKE ? OR email LIKE ?";

        try (Connection conn = this.personaDAO.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
            String querySQL = "%" + parametro + "%";
            sentence.setString(1, querySQL);
            sentence.setString(2, querySQL);
            sentence.setString(3, querySQL);

            try (ResultSet resultSet = sentence.executeQuery()) {
                while (resultSet.next()) {
                    Administrador entity = cearEntity(resultSet);
                    listEntity.add(entity);
                }
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar Administrador por parametro: " + parametro);
        }
        return listEntity;
    }

    @Override
    public boolean delete(Long id) {
        String deleteSQL = "DELETE FROM administradores WHERE id=?";
        try (Connection conn = this.personaDAO.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(deleteSQL)) {
            sentence.setLong(1, id);
            sentence.executeUpdate();
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al eliminar Administrador ID: " + id);
            return false;
        }
        return true;
    }

    private void setSentenceEntity(final PreparedStatement sentence, Administrador entity) throws SQLException {
        sentence.setString(1, entity.getNombre());
        sentence.setString(2, entity.getApellido());
        sentence.setString(3, entity.getEmail());
        sentence.setString(4, entity.getTelefono());
        sentence.setLong(5, entity.getUsuario().getId());
    }

    private Administrador cearEntity(final ResultSet resultSet) throws SQLException {
        Administrador entity = new Administrador();
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
