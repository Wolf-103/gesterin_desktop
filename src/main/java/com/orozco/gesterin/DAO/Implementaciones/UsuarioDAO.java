package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.ConnectionMysql;
import com.orozco.gesterin.DAO.GenericDAO;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
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

    private final ConnectionMysql connection;

    public UsuarioDAO() {
        this.connection = new ConnectionMysql();
    }

    @Override
    public boolean save(Usuario usuario) {
        try {
            String registarSQL = "INSERT INTO usuarios(nombre, contrasena, estado, rol_id) "
                    + " VALUES(?,?,?,?)";

            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(registarSQL, Statement.RETURN_GENERATED_KEYS)) {
                this.setSentenceUsuario(sentence, usuario);

                if (sentence.executeUpdate() == 0) {
                    throw new SQLException("No se ha modificadado ninguna fila.");
                }

                try (ResultSet generatedKeys = sentence.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setId(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("No fue posible obtener el ID generado.");
                    }
                }
            }

        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al registrar usuario Nombre: " + usuario.getNombre());
            return false;
        }

        return true;
    }

    @Override
    public Usuario findById(Long id) {
        String findSQL = "SELECT * FROM usuarios WHERE id=?";
        Usuario entity = null;
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                if (rs.next()) {
                    entity = this.setUsuario(rs);
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
                Usuario entity = this.setUsuario(resultSet);
                listEntities.add(entity);
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar todos los usuario registrados");
        }
        return listEntities;
    }

    @Override
    public boolean update(Usuario entity) {
        String updateSQL = "UPDATE usuarios SET nombre=?, contrasena=?, estado=?, rol_id=? WHERE id=?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            this.setSentenceUsuario(sentence, entity);
            sentence.setLong(5, entity.getId());

            sentence.executeUpdate();
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar usuario ID: " + entity.getId());
            return false;
        }
        return true;
    }

    public List<Usuario> findAllByParams(String parametro) {
        List<Usuario> listEntities = new ArrayList<>();
        String request = "SELECT * FROM usuarios WHERE nombre LIKE ? ";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
            String querySQL = "%" + parametro + "%";
            sentence.setString(1, querySQL);

            try (ResultSet resultSet = sentence.executeQuery()) {
                while (resultSet.next()) {
                    Usuario entity = setUsuario(resultSet);
                    listEntities.add(entity);
                }
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar usuarios por parametro: " + parametro);
        }
        return listEntities;
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
        sentence.setLong(4, usuario.getRol_id());
    }

    private Usuario setUsuario(final ResultSet resultSet) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getLong(1));
        usuario.setNombre(resultSet.getString(2));
        usuario.setConstrasena(resultSet.getString(3));
        usuario.setEstado(resultSet.getBoolean(4));
        usuario.setRol_id(resultSet.getLong(5));
   
        return usuario;
    }

}
