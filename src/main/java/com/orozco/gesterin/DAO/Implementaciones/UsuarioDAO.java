package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.GenericDAO;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
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
 * @fecha 03 Oct. 2024
 * @description Sistema GESTERIN
 */
public class UsuarioDAO implements GenericDAO<Usuario, Long> {

    private final ConnectionMysqlImpl connection;
    private final RolDAO rolDAO;

    public UsuarioDAO() {
        this.connection = new ConnectionMysqlImpl();
        this.rolDAO = new RolDAO();
    }

    @Override
    public Usuario save(Usuario entity) {
        try {
            String registarSQL = "INSERT INTO usuarios(nombre, contrasena, estado, rol_id) "
                    + " VALUES(?,?,?,?)";

            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(registarSQL, Statement.RETURN_GENERATED_KEYS)) {
                this.setSentenceUsuario(sentence, entity);

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
            ControllerExceptionHandler.handleError(ex, "Error al registrar usuario Nombre: " + entity.getNombre());
            return null;
        }

        return this.findById(entity.getId());
    }

    @Override
    public Usuario findById(Long id) {
        String findSQL = "SELECT * FROM usuarios WHERE id=?";
        Usuario entity = null;
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                if (rs.next()) {
                    Rol rol = this.rolDAO.findById(rs.getLong("rol_id"));
                    entity = this.setUsuario(rs, rol);
                }
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar usuario por ID: " + id);
        }
        return entity;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> listEntities = new ArrayList<>();
        String request = "SELECT * FROM usuarios";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request); ResultSet resultSet = sentence.executeQuery()) {

            while (resultSet.next()) {
                Rol rol = this.rolDAO.findById(resultSet.getLong("rol_id"));
                Usuario entity = this.setUsuario(resultSet, rol);
                listEntities.add(entity);
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar todos los usuario registrados");
        }
        return listEntities;
    }

    @Override
    public Usuario update(Usuario entity) {
        String updateSQL = "UPDATE usuarios SET nombre=?, contrasena=?, estado=?, rol_id=? WHERE id=?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            this.setSentenceUsuario(sentence, entity);
            sentence.setLong(5, entity.getId());

            sentence.executeUpdate();
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar usuario ID: " + entity.getId());
            return null;
        }
        return this.findById(entity.getId());
    }

    public List<Usuario> findAllByParams(String parametro) {
        List<Usuario> listEntities = new ArrayList<>();
        String request = "SELECT * FROM usuarios WHERE nombre LIKE ? ";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
            String querySQL = "%" + parametro + "%";
            sentence.setString(1, querySQL);

            try (ResultSet resultSet = sentence.executeQuery()) {
                while (resultSet.next()) {
                    Rol rol = this.rolDAO.findById(resultSet.getLong("rol_id"));
                    Usuario entity = setUsuario(resultSet, rol);
                    listEntities.add(entity);
                }
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar usuarios por parametro: " + parametro);
        }
        return listEntities;
    }

    /**
     * Buscar por nombre de usuario
     *
     * @param username
     * @return
     */
    public Usuario findByUsername(String username) {
        Usuario entitie = null;
        String request = "SELECT * FROM usuarios WHERE nombre = ? ";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
            sentence.setString(1, username);

            try (ResultSet resultSet = sentence.executeQuery()) {
                while (resultSet.next()) {
                    Rol rol = this.rolDAO.findById(resultSet.getLong("rol_id"));
                    entitie = setUsuario(resultSet, rol);
                }
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar usuarios por nombre de usuaro: " + username);
            return null;
        }
        return entitie;
    }

    @Override
    public boolean delete(Long id) {
        String deleteSQL = "DELETE FROM usuarios WHERE id=?";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(deleteSQL)) {
            sentence.setLong(1, id);
            sentence.executeUpdate();
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al eliminar usuario ID: " + id);
            return false;
        }
        return true;
    }

    private void setSentenceUsuario(final PreparedStatement sentence, Usuario usuario) throws SQLException {
        sentence.setString(1, usuario.getNombre());
        sentence.setString(2, usuario.getConstrasena());
        sentence.setBoolean(3, usuario.getEstado());
        sentence.setLong(4, usuario.getRol().getId());
    }

    private Usuario setUsuario(final ResultSet resultSet, Rol rol) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getLong(1));
        usuario.setNombre(resultSet.getString(2));
        usuario.setConstrasena(resultSet.getString(3));
        usuario.setEstado(resultSet.getBoolean(4));
        usuario.setRol(rol);

        return usuario;
    }

}
