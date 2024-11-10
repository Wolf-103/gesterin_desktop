package com.orozco.gesterin.DAO.Implementaciones;

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

    ConnectionMysqlImpl connection;

    public PersonaDAO() {
        this.connection = new ConnectionMysqlImpl();
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

    public Long saveWithConnection(Persona entity, Connection conn) {
        try {
            String registarSQL = "INSERT INTO people(nombre, apellido, email, telefono) "
                    + " VALUES(?,?,?,?)";

            try (PreparedStatement sentence = conn.prepareStatement(registarSQL, Statement.RETURN_GENERATED_KEYS)) {
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
        return entity.getId();
    }

    @Override
    public Persona findById(Long id) {
        String findSQL = "SELECT * FROM people WHERE id=?";
        Persona entity = null;
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                if (rs.next()) {
                    entity = this.ceateOnlyPerson(rs);
                }
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar persona por ID: " + id);
        }
        return entity;
    }

    public Persona findUserById(Long id) {
        String findSQL = "SELECT p.id AS id, p.nombre AS nombre, p.apellido AS apellido, "
                + " p.email AS email, p.telefono AS telefono, "
                + " u.id AS usuario_id, u.nombre AS usuario_nombre, u.estado AS estado, u.contrasena AS usuario_pass, "
                + " r.id AS rol_id, r.nombre AS rol_nombre, r.descripcion AS rol_descripcion "
                + "FROM people p "
                + "JOIN usuarios u ON p.id = u.id "
                + "JOIN roles r ON u.rol_id = r.id "
                + "LEFT JOIN administradores a ON p.id = a.id "
                + "LEFT JOIN profesionales pr ON p.id = pr.id "
                + "WHERE id = ?";
        Persona entity = null;
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                while (rs.next()) {
                    entity = this.cearEntity(rs);
                    Rol rol = new Rol(rs.getLong("rol_id"), rs.getString("rol_nombre"), rs.getString("rol_descripcion"));
                    Usuario usuario = new Usuario(rs.getLong("usuario_id"), rs.getString("usuario_nombre"), rs.getBoolean("estado"), rol);
                    usuario.setRol(rol);
                    usuario.setConstrasena(rs.getString("usuario_pass"));
                    if ((entity instanceof Administrador admin)) {
                        admin.setUsuario(usuario);
                        entity = admin;
                    } else if (entity instanceof Profesional profesional) {
                        profesional.setUsuario(usuario);
                        entity = profesional;
                    }
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
        String updateSQL = "UPDATE people SET nombre=?, apellido=?, email=?, telefono=? WHERE id=?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            this.setSentenceEntity(sentence, entity);
            sentence.setLong(5, entity.getId());
            sentence.executeUpdate();
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar persona ID: " + entity.getId());
            return null;
        }
        return this.findById(entity.getId());
    }

    public boolean updateWithConnection(Persona entity, Connection conn) {
        String updateSQL = "UPDATE people SET nombre=?, apellido=?, email=?, telefono=? WHERE id=?";

        try (PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            this.setSentenceEntity(sentence, entity);
            sentence.setLong(5, entity.getId());
            sentence.executeUpdate();
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar persona ID: " + entity.getId());
            return false;
        }
        return true;
    }

    public List<Persona> findAllByParams(String parametro) {
        List<Persona> listEntity = new ArrayList<>();
        String sql = "SELECT p.id AS id, p.nombre AS nombre, p.apellido AS apellido, "
                + " p.email AS email, p.telefono AS telefono, "
                + " u.id AS usuario_id, u.nombre AS usuario_nombre, u.estado AS estado, u.contrasena AS usuario_pass, "
                + " r.id AS rol_id, r.nombre AS rol_nombre, r.descripcion AS rol_descripcion "
                + "FROM people p "
                + "JOIN usuarios u ON p.id = u.id "
                + "JOIN roles r ON u.rol_id = r.id "
                + "LEFT JOIN administradores a ON p.id = a.id "
                + "LEFT JOIN profesionales pr ON p.id = pr.id "
                + "WHERE p.nombre LIKE ? OR p.apellido LIKE ? OR p.email LIKE ?";
        
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(sql)) {
            String querySQL = "%" + parametro + "%";
            sentence.setString(1, querySQL);
            sentence.setString(2, querySQL);
            sentence.setString(3, querySQL);

            try (ResultSet rs = sentence.executeQuery()) {
                while (rs.next()) {
                    Persona persona = this.cearEntity(rs);
                    Rol rol = new Rol(rs.getLong("rol_id"), rs.getString("rol_nombre"), rs.getString("rol_descripcion"));
                    Usuario usuario = new Usuario(rs.getLong("usuario_id"), rs.getString("usuario_nombre"), rs.getBoolean("estado"), rol);
                    usuario.setRol(rol);
                    usuario.setConstrasena(rs.getString("usuario_pass"));

                    if ((persona instanceof Administrador admin)) {
                        admin.setUsuario(usuario);
                        persona = admin;
                    } else if (persona instanceof Profesional profesional) {
                        profesional.setUsuario(usuario);
                        persona = profesional;
                    } else {
                        continue;
                    }

                    listEntity.add(persona);
                }
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar people por parametro: " + parametro);
        }
        return listEntity;
    }

    public List<Persona> findAllPeopleUsers() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT p.id AS id, p.nombre AS nombre, p.apellido AS apellido, "
                + " p.email AS email, p.telefono AS telefono, "
                + " u.id AS usuario_id, u.nombre AS usuario_nombre, u.estado AS estado, u.contrasena AS usuario_pass, "
                + " r.id AS rol_id, r.nombre AS rol_nombre, r.descripcion AS rol_descripcion "
                + "FROM people p "
                + "JOIN usuarios u ON p.id = u.id "
                + "JOIN roles r ON u.rol_id = r.id "
                + "LEFT JOIN administradores a ON p.id = a.id "
                + "LEFT JOIN profesionales pr ON p.id = pr.id";

        try (Connection conn = this.connection.getConn(); Statement sentence = conn.createStatement(); ResultSet rs = sentence.executeQuery(sql)) {
            while (rs.next()) {
                Persona persona = this.cearEntity(rs);
                Rol rol = new Rol(rs.getLong("rol_id"), rs.getString("rol_nombre"), rs.getString("rol_descripcion"));
                Usuario usuario = new Usuario(rs.getLong("usuario_id"), rs.getString("usuario_nombre"), rs.getBoolean("estado"), rol);
                usuario.setRol(rol);
                usuario.setConstrasena(rs.getString("usuario_pass"));

                if ((persona instanceof Administrador admin)) {
                    admin.setUsuario(usuario);
                    persona = admin;
                } else if (persona instanceof Profesional profesional) {
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

    /**
     * Buscar todos los administradores por nombre, apellido o email
     *
     * @return List de Administrador
     */
    public List<Administrador> findAllPeopleAdministrador() {
        List<Administrador> administradores = new ArrayList<>();
        String request = "SELECT p.id AS id, p.nombre AS nombre, p.apellido AS apellido, "
                + " p.email AS email, p.telefono AS telefono, "
                + " u.id AS usuario_id, u.nombre AS usuario_nombre, u.estado AS estado, u.contrasena AS usuario_pass, "
                + " r.id AS rol_id, r.nombre AS rol_nombre, r.descripcion AS rol_descripcion "
                + "FROM people p "
                + "JOIN usuarios u ON p.id = u.id "
                + "JOIN roles r ON u.rol_id = r.id "
                + "LEFT JOIN administradores a ON p.id = a.id "
                + "WHERE r.nombre = 'ADMINISTRADOR' ";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request); ResultSet rs = sentence.executeQuery(request)) {
            while (rs.next()) {
                Persona persona = this.cearEntity(rs);
                Rol rol = new Rol(rs.getLong("rol_id"), rs.getString("rol_nombre"), rs.getString("rol_descripcion"));
                Usuario usuario = new Usuario(rs.getLong("usuario_id"), rs.getString("usuario_nombre"), rs.getBoolean("estado"), rol);
                usuario.setRol(rol);
                usuario.setConstrasena(rs.getString("usuario_pass"));

                if ((persona instanceof Administrador admin)) {
                    admin.setUsuario(usuario);
                } else {
                    continue;
                }

                administradores.add(admin);
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar personas de tipo Administrador");
        }
        return administradores;
    }

    /**
     * Buscar todos los administradores por nombre, apellido o email
     *
     * @param parametro: tipo String nombre, apellido o email
     * @return List de Administrador
     */
    public List<Administrador> findAllPeopleAdministrador(String parametro) {
        List<Administrador> administradores = new ArrayList<>();
        String request = "SELECT p.id AS id, p.nombre AS nombre, p.apellido AS apellido, "
                + " p.email AS email, p.telefono AS telefono, "
                + " u.id AS usuario_id, u.nombre AS usuario_nombre, u.estado AS estado, u.contrasena AS usuario_pass, "
                + " r.id AS rol_id, r.nombre AS rol_nombre, r.descripcion AS rol_descripcion "
                + "FROM people p "
                + "JOIN usuarios u ON p.id = u.id "
                + "JOIN roles r ON u.rol_id = r.id "
                + "LEFT JOIN administradores a ON p.id = a.id "
                + "WHERE r.nombre = ADMINISTRADOR AND "
                + "nombre LIKE ? OR apellido LIKE ? OR email LIKE ?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
            String querySQL = "%" + parametro + "%";
            sentence.setString(1, querySQL);
            sentence.setString(2, querySQL);
            sentence.setString(3, querySQL);

            try (ResultSet rs = sentence.executeQuery()) {
                while (rs.next()) {
                    Persona persona = this.cearEntity(rs);
                    Rol rol = new Rol(rs.getLong("rol_id"), rs.getString("rol_nombre"), rs.getString("rol_descripcion"));
                    Usuario usuario = new Usuario(rs.getLong("usuario_id"), rs.getString("usuario_nombre"), rs.getBoolean("estado"), rol);
                    usuario.setRol(rol);
                    usuario.setConstrasena(rs.getString("usuario_pass"));

                    if ((persona instanceof Administrador admin)) {
                        admin.setUsuario(usuario);
                    } else {
                        continue;
                    }

                    administradores.add(admin);
                }
            }

        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar personas de tipo Administrador");
        }
        return administradores;
    }

    /**
     * Buscar todos los profesionales
     *
     * @return List Profesioanl
     */
    public List<Profesional> findAllPeopleProfesional() {
        List<Profesional> profesionales = new ArrayList<>();
        String request = "SELECT p.id AS id, p.nombre AS nombre, p.apellido AS apellido, "
                + " p.email AS email, p.telefono AS telefono, "
                + " u.id AS usuario_id, u.nombre AS usuario_nombre, u.estado AS estado, u.contrasena AS usuario_pass, "
                + " r.id AS rol_id, r.nombre AS rol_nombre, r.descripcion AS rol_descripcion "
                + "FROM people p "
                + "JOIN usuarios u ON p.id = u.id "
                + "JOIN roles r ON u.rol_id = r.id "
                + "LEFT JOIN profesionales pr ON p.id = pr.id "
                + "WHERE r.nombre = 'PROFESIONAL' ";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request); ResultSet rs = sentence.executeQuery(request)) {
            while (rs.next()) {
                Persona persona = this.cearEntity(rs);
                Rol rol = new Rol(rs.getLong("rol_id"), rs.getString("rol_nombre"), rs.getString("rol_descripcion"));
                Usuario usuario = new Usuario(rs.getLong("usuario_id"), rs.getString("usuario_nombre"), rs.getBoolean("estado"), rol);
                usuario.setRol(rol);
                usuario.setConstrasena(rs.getString("usuario_pass"));

                if (persona instanceof Profesional profesional) {
                    profesional.setUsuario(usuario);
                } else {
                    continue;
                }

                profesionales.add(profesional);
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar personas de tipo Profesional");
        }
        return profesionales;
    }

    /**
     * Buscar todos los profesionales por nombre, apellido o email
     *
     * @param parametro: tipo String nombre, apellido o email
     * @return List Profesioanl
     */
    public List<Profesional> findAllPeopleProfesional(String parametro) {
        List<Profesional> profesionales = new ArrayList<>();
        String request = "SELECT p.id AS id, p.nombre AS nombre, p.apellido AS apellido, "
                + " p.email AS email, p.telefono AS telefono, "
                + " u.id AS usuario_id, u.nombre AS usuario_nombre, u.estado AS estado, u.contrasena AS usuario_pass, "
                + " r.id AS rol_id, r.nombre AS rol_nombre, r.descripcion AS rol_descripcion "
                + "FROM people p "
                + "JOIN usuarios u ON p.id = u.id "
                + "JOIN roles r ON u.rol_id = r.id "
                + "LEFT JOIN profesionales pr ON p.id = pr.id "
                + "WHERE r.nombre = PROFESIONAL "
                + "nombre LIKE ? OR apellido LIKE ? OR email LIKE ?";

        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
            String querySQL = "%" + parametro + "%";
            sentence.setString(1, querySQL);
            sentence.setString(2, querySQL);
            sentence.setString(3, querySQL);

            try (ResultSet rs = sentence.executeQuery()) {
                while (rs.next()) {
                    Persona persona = this.cearEntity(rs);
                    Rol rol = new Rol(rs.getLong("rol_id"), rs.getString("rol_nombre"), rs.getString("rol_descripcion"));
                    Usuario usuario = new Usuario(rs.getLong("usuario_id"), rs.getString("usuario_nombre"), rs.getBoolean("estado"), rol);
                    usuario.setRol(rol);
                    usuario.setConstrasena(rs.getString("usuario_pass"));

                    if (persona instanceof Profesional profesional) {
                        profesional.setUsuario(usuario);
                    } else {
                        continue;
                    }

                    profesionales.add(profesional);
                }
            }

        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar personas de tipo Profesional");
        }
        return profesionales;
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

    private Persona cearEntity(final ResultSet resultSet) throws SQLException {
        String rolNombre = resultSet.getString("rol_nombre");
        Persona entity = switch (rolNombre) {
            case "ADMINISTRADOR" ->
                new Administrador();
            case "PROFESIONAL" ->
                new Profesional();
            default ->
                new PersonaDTO();
        };

        entity.setId(resultSet.getLong("id"));
        entity.setEmail(resultSet.getString("email"));
        entity.setTelefono(resultSet.getString("telefono"));
        entity.setNombre(resultSet.getString("nombre"));
        entity.setApellido(resultSet.getString("apellido"));

        return entity;
    }

    private Persona ceateOnlyPerson(final ResultSet resultSet) throws SQLException {
        Persona entity = new PersonaDTO();
        entity.setId(resultSet.getLong("id"));
        entity.setEmail(resultSet.getString("email"));
        entity.setTelefono(resultSet.getString("telefono"));
        entity.setNombre(resultSet.getString("nombre"));
        entity.setApellido(resultSet.getString("apellido"));
        return entity;
    }

    /**
     * Validar si existe persona con el id
     *
     * @param id: identifiador de la persona
     * @return boolean
     */
    public boolean existsById(Long id) {
        String existsSQL = "SELECT 1 FROM people WHERE id = ?";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(existsSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al verificar existencia de la persona con ID: " + id);
            return false;
        }
    }

    /**
     * Validar si existe persona con el email
     *
     * @param email : email de la persona
     * @return boolean
     */
    public boolean existsByEmail(String email) {
        if (email != null) {
            String existsSQL = "SELECT 1 FROM people WHERE email = ?";
            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(existsSQL)) {
                sentence.setString(1, email);
                try (ResultSet rs = sentence.executeQuery()) {
                    return rs.next();
                }
            } catch (SQLException ex) {
                ControllerExceptionHandler.handleError(ex, "Error al verificar existencia de la persona con CORREO ELECTRÓNICO: " + email);
                return false;
            }
        }
        return false;
    }

    /**
     * Validar si existe persona con el telefono
     *
     * @param telefono : email de la persona
     * @return boolean
     */
    public boolean existsByTelephone(String telefono) {
        if (telefono != null) {
            String existsSQL = "SELECT 1 FROM people WHERE telefono = ?";
            try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(existsSQL)) {
                sentence.setString(1, telefono);
                try (ResultSet rs = sentence.executeQuery()) {
                    return rs.next();
                }
            } catch (SQLException ex) {
                ControllerExceptionHandler.handleError(ex, "Error al verificar existencia de la persona con el teléfono: " + telefono);
                return false;
            }
        }
        return false;
    }
}
