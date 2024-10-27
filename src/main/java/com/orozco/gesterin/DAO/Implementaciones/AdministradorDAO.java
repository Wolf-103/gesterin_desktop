package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.GenericDAO;
import com.orozco.gesterin.dto.PersonaDTO;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.Administrador;
import com.orozco.gesterin.model.Persona;
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
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class AdministradorDAO implements GenericDAO<Administrador, Long> {

    private final PersonaDAO personaDAO;
    private final UsuarioDAO usuarioDAO;
    private Connection conn = null;

    public AdministradorDAO(PersonaDAO personaDAO, UsuarioDAO usuarioDAO) {
        this.personaDAO = personaDAO;
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public Administrador save(Administrador entity) {
        try {
            conn = this.personaDAO.connection.getConn();
            conn.setAutoCommit(false);

            Long idPersona = this.personaDAO.saveWithConnection(new PersonaDTO(
                    entity.getNombre(), entity.getApellido(), entity.getEmail(), entity.getTelefono()),
                    conn);
            if (idPersona == null) {
                throw new SQLException("Error al guardar Persona.");
            }
            entity.setId(idPersona);

            Long idUsuario = this.usuarioDAO.saveWithConnection(entity.getUsuario(), conn);
            if (idUsuario == null) {
                throw new SQLException("Error al guardar Usuario.");
            }
            entity.getUsuario().setId(idUsuario);

            String registarSQL = "INSERT INTO administradores(id, usuario_id) VALUES(?, ?)";
            try (PreparedStatement sentence = conn.prepareStatement(registarSQL, Statement.RETURN_GENERATED_KEYS)) {
                sentence.setLong(1, entity.getId());
                sentence.setLong(2, entity.getUsuario().getId());
                if (sentence.executeUpdate() == 0) {
                    throw new SQLException("No se ha modificado ninguna fila.");
                }
//                try (ResultSet generatedKeys = sentence.getGeneratedKeys()) {
//                    if (!generatedKeys.next()) {
//                        throw new SQLException("No fue posible obtener el ID generado.");
//                    }
//                }
            }

            conn.commit();

        } catch (NullPointerException | SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    ControllerExceptionHandler.handleError(rollbackEx, "Error al hacer rollback.");
                }
            }
            ControllerExceptionHandler.handleError(ex, "Error al registrar Administrador email: " + entity.getEmail());
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ex) {
                    ControllerExceptionHandler.handleError(ex, "Error al restablecer auto-commit.");
                }
            }
        }
        return this.findById(entity.getId());
    }

    @Override
    public Administrador findById(Long id) {
        String findSQL = "SELECT p.id AS id, p.nombre AS nombre, p.apellido AS apellido, "
                + "p.email AS email, p.telefono AS telefono, "
                + "u.id AS usuario_id, u.nombre AS usuario_nombre, u.estado AS estado, u.contrasena AS usuario_pass, "
                + "r.id AS rol_id, r.nombre AS rol_nombre, r.descripcion AS rol_descripcion, "
                + "a.id AS admin_id "
                + "FROM people p "
                + "LEFT JOIN usuarios u ON p.id = u.id "
                + "LEFT JOIN roles r ON u.rol_id = r.id "
                + "LEFT JOIN administradores a ON p.id = a.id "
                + "WHERE p.id = ?";
        Persona entity = null;
        try (Connection getCon = this.personaDAO.connection.getConn(); PreparedStatement sentence = getCon.prepareStatement(findSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                if (rs.next()) {
                    entity = this.createEntity(rs);
                    Rol rol = new Rol(rs.getLong("rol_id"), rs.getString("rol_nombre"), rs.getString("rol_descripcion"));
                    Usuario usuario = new Usuario(rs.getLong("usuario_id"), rs.getString("usuario_nombre"), rs.getBoolean("estado"), rol);
                    usuario.setRol(rol);
                    usuario.setConstrasena(rs.getString("usuario_pass"));
                    
                    if (rs.getLong("admin_id") > 0 && entity instanceof Administrador admin) {
                        admin.setUsuario(usuario);
                        entity = admin;
                    }
                }
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar administrador por ID: " + id);
        }
        return (Administrador) entity;
    }

    @Override
    public List<Administrador> findAll() {
        return this.personaDAO.findAllPeopleAdministrador();
    }

    @Override
    public Administrador update(Administrador entity) {
        try {
            conn = this.personaDAO.connection.getConn();
            conn.setAutoCommit(false);

            boolean personaActualizada = this.personaDAO.updateWithConnection(new PersonaDTO(
                    entity.getId(), entity.getNombre(), entity.getApellido(), entity.getEmail(), entity.getTelefono()), conn);
            if (!personaActualizada) {
                throw new SQLException("Error al actualizar Persona.");
            }

            boolean usuarioActualizado = this.usuarioDAO.updateWithConnection(entity.getUsuario(), conn);
            if (!usuarioActualizado) {
                throw new SQLException("Error al actualizar Usuario.");
            }

            conn.commit(); 
        } catch (NullPointerException | SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    ControllerExceptionHandler.handleError(rollbackEx, "Error al hacer rollback.");
                }
            }
            ControllerExceptionHandler.handleError(ex, "Error al actualizar Administrador email: " + entity.getEmail());
            return null;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ex) {
                    ControllerExceptionHandler.handleError(ex, "Error al restablecer auto-commit.");
                }
            }
        }
        return this.findById(entity.getId());
    }

    public List<Administrador> findAllByParams(String parametro) {
        List<Administrador> listEntity = new ArrayList<>();
        String request = "SELECT * FROM administradores WHERE nombre LIKE ? OR apellido LIKE ? OR email LIKE ?";

        try (Connection getCon = this.personaDAO.connection.getConn(); PreparedStatement sentence = getCon.prepareStatement(request)) {
            String querySQL = "%" + parametro + "%";
            sentence.setString(1, querySQL);
            sentence.setString(2, querySQL);
            sentence.setString(3, querySQL);

            try (ResultSet resultSet = sentence.executeQuery()) {
                while (resultSet.next()) {
                    Administrador entity = createEntity(resultSet);
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
        sentence.setLong(1, entity.getId());
        sentence.setLong(2, entity.getUsuario().getId());
    }

    private Administrador createEntity(final ResultSet resultSet) throws SQLException {
        Administrador entity = new Administrador();
        entity.setId(resultSet.getLong("id"));
        entity.setNombre(resultSet.getString("nombre"));
        entity.setApellido(resultSet.getString("apellido"));
        entity.setEmail(resultSet.getString("email"));
        entity.setTelefono(resultSet.getString("telefono"));

        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getLong("usuario_id"));
        usuario.setNombre(resultSet.getString("usuario_nombre"));
        usuario.setEstado(resultSet.getBoolean("estado"));

        Rol rol = new Rol(resultSet.getLong("rol_id"), resultSet.getString("rol_nombre"), resultSet.getString("rol_descripcion"));
        usuario.setRol(rol);

        entity.setUsuario(usuario);

        return entity;
    }

}
