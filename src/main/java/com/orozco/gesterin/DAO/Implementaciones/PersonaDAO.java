package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.ConnectionMysql;
import com.orozco.gesterin.DAO.GenericDAO;
import com.orozco.gesterin.dto.PersonaDTO;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.Administrador;
import com.orozco.gesterin.model.Persona;
import com.orozco.gesterin.model.Profesional;
import com.orozco.gesterin.model.Rol;
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
 * @fecha 15 Oct. 2024
 * @description Sistema GESTERIN
 */
public class PersonaDAO implements GenericDAO<Persona, Long> {

    ConnectionMysql connection;

    public PersonaDAO() {
        this.connection = new ConnectionMysql();
    }

    @Override
    public Persona save(Persona entity) {
        try {
            String registarSQL = "INSERT INTO people(nombre, apellido, email, telefono) "
                    + " VALUES(?,?,?,?)";

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
            ControllerExceptionHandler.handleError(ex, "Error al registrar Persona email: " + entity.getEmail());
            return null;
        }
        return this.findById(entity.getId());
    }

    @Override
    public Persona findById(Long id) {
        String findSQL = "SELECT * FROM people WHERE id=?";
        Persona entity = null;
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                if (rs.next()) {
                    entity = this.cearEntity(rs);
                }
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar persona por ID: " + id);
        }
        return entity;
    }

    @Override
    public List<Persona> findAll() {
        List<Persona> listaEntities = new ArrayList<>();
        String request = "SELECT * FROM people";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request); ResultSet resultSet = sentence.executeQuery()) {

            while (resultSet.next()) {
                Persona entity = this.cearEntity(resultSet);
                listaEntities.add(entity);
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar todos los persona registrados");
        }
        return listaEntities;
    }

    @Override
    public Persona update(Persona entity) {
        String updateSQL = "UPDATE persona SET nombre=?, apellido=?, email=?, telefono=? WHERE id=?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            this.setSentenceEntity(sentence, entity);
            sentence.setLong(6, entity.getId());
            sentence.executeUpdate();
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar persona ID: " + entity.getId());
            return null;
        }
        return this.findById(entity.getId());
    }

    public List<Persona> findAllByParams(String parametro) {
        List<Persona> listEntity = new ArrayList<>();
        String request = "SELECT * FROM people WHERE nombre LIKE ? OR apellido LIKE ? OR email LIKE ?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
            String querySQL = "%" + parametro + "%";
            sentence.setString(1, querySQL);
            sentence.setString(2, querySQL);
            sentence.setString(3, querySQL);

            try (ResultSet resultSet = sentence.executeQuery()) {
                while (resultSet.next()) {
                    Persona entity = cearEntity(resultSet);
                    listEntity.add(entity);
                }
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar people por parametro: " + parametro);
        }
        return listEntity;
    }

    public List<Persona> findAllPeopleUsers() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT p.id AS persona_id, p.nombre AS nombre, p.apellido AS apellido, "
                + " p.email AS email, p.telefono AS telefono, "
                + " u.id AS usuario_id, u.nombre AS usuario_nombre, u.estado AS estado, "
                + " r.id AS rol_id, r.nombre AS rol_nombre, r.descripcion AS rol_descripcion "
                + "FROM people p "
                + "JOIN usuarios u ON p.id = u.id "
                + "JOIN roles r ON u.rol_id = r.id "
                + "LEFT JOIN administradores a ON p.id = a.id "
                + "LEFT JOIN profesionales pr ON p.id = pr.id";

        try (Connection conn = this.connection.getConn(); Statement sentence = conn.createStatement(); ResultSet rs = sentence.executeQuery(sql)) {
            while (rs.next()) {
                String rolNombre = rs.getString("rol_nombre");
                Persona persona;
                Rol rol = new Rol(rs.getLong("rol_id"), rs.getString("rol_nombre"), rs.getString("rol_descripcion"));
                Usuario usuario = new Usuario(rs.getLong("usuario_id"), rs.getString("usuario_nombre"), rs.getBoolean("estado"), rol);
                usuario.setRol(rol);

                if ("ADMINISTRADOR".equals(rolNombre)) {
                    Administrador admin = new Administrador();
                    admin.setId(rs.getLong("persona_id"));
                    admin.setNombre(rs.getString("nombre"));
                    admin.setApellido(rs.getString("apellido"));
                    admin.setEmail(rs.getString("email"));
                    admin.setTelefono(rs.getString("telefono"));
                    admin.setUsuario(usuario);
                    persona = admin;
                } else if ("PROFESIONAL".equals(rolNombre)) {
                    Profesional profesional = new Profesional();
                    profesional.setId(rs.getLong("persona_id"));
                    profesional.setNombre(rs.getString("nombre"));
                    profesional.setApellido(rs.getString("apellido"));
                    profesional.setEmail(rs.getString("email"));
                    profesional.setTelefono(rs.getString("telefono"));
                    profesional.setUsuario(usuario);
                    persona = profesional;
                } else {
                    continue;
                }

                personas.add(persona);
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar personas de tipo administrador y profesional ");
        }
        return personas;
    }

    @Override
    public boolean delete(Long id) {
        String deleteSQL = "DELETE FROM people WHERE id=?";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(deleteSQL)) {
            sentence.setLong(1, id);
            sentence.executeUpdate();
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al eliminar people ID: " + id);
            return false;
        }
        return true;
    }

    private void setSentenceEntity(final PreparedStatement sentence, Persona entity) throws SQLException {
        sentence.setString(1, entity.getNombre());
        sentence.setString(2, entity.getApellido());
        sentence.setString(3, entity.getEmail());
        sentence.setString(4, entity.getTelefono());
    }

    private PersonaDTO cearEntity(final ResultSet resultSet) throws SQLException {
        PersonaDTO entity = new PersonaDTO();
        entity.setId(resultSet.getLong("id"));
        entity.setEmail(resultSet.getString("email"));
        entity.setTelefono(resultSet.getString("telefono"));
        entity.setNombre(resultSet.getString("nombre"));
        entity.setApellido(resultSet.getString("apellido"));
        return entity;
    }
}
