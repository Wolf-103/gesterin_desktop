package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.GenericDAO;
import com.orozco.gesterin.dto.PersonaDTO;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.Especialidad;
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
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class ProfesionalDAO implements GenericDAO<Profesional, Long> {

    private final PersonaDAO personaDAO;

    public ProfesionalDAO(PersonaDAO personaDAO) {
        this.personaDAO = new PersonaDAO();
    }

    @Override
    public Profesional save(Profesional entity) {
        try {
            Persona persona = this.personaDAO.save(new PersonaDTO(
                    entity.getNombre(), entity.getApellido(), entity.getEmail(), entity.getTelefono()));
            entity.setId(persona.getId());
            String registarSQL = "INSERT INTO profesionales(id, estado, usuario_id) "
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
            ControllerExceptionHandler.handleError(ex, "Error al registrar profesional email: " + entity.getEmail());
            return null;
        }

        return this.findById(entity.getId());
    }

    @Override
    public Profesional findById(Long id) {
        String findSQL = "SELECT p.id AS id, p.nombre AS nombre, p.apellido AS apellido, "
                + "p.email AS email, p.telefono AS telefono, "
                + "u.id AS usuario_id, u.nombre AS usuario_nombre, u.estado AS estado, "
                + "r.id AS rol_id, r.nombre AS rol_nombre, r.descripcion AS rol_descripcion, "
                + "e.id AS especialidad_id, e.nombre AS especialidad_nombre, e.descripcion AS especialidad_descripcion "
                + "FROM people p "
                + "JOIN usuarios u ON p.id = u.id "
                + "JOIN roles r ON u.rol_id = r.id "
                + "JOIN profesionales pr ON p.id = pr.id "
                + "LEFT JOIN profesional_especialidades pe ON pr.id = pe.profesional_id "
                + "LEFT JOIN especialidades e ON pe.especialidad_id = e.id "
                + "WHERE pr.id=?";
        Profesional entity = null;
        try (Connection conn = this.personaDAO.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                if (rs.next()) {
                    Profesional profesional = new Profesional();
                    profesional.setId(rs.getLong("id"));
                    profesional.setNombre(rs.getString("nombre"));
                    profesional.setApellido(rs.getString("apellido"));
                    profesional.setEmail(rs.getString("email"));
                    profesional.setTelefono(rs.getString("telefono"));
                    Rol rol = new Rol(rs.getLong("rol_id"), rs.getString("rol_nombre"), rs.getString("rol_descripcion"));
                    Usuario usuario = new Usuario(rs.getLong("usuario_id"), rs.getString("usuario_nombre"), rs.getBoolean("estado"), rol);
                    usuario.setRol(rol);
                    profesional.setUsuario(usuario);
                    entity = profesional;

                    do {
                        Long especialidadId = rs.getLong("especialidad_id");
                        if (especialidadId != null) {
                            Especialidad especialidad = new Especialidad();
                            especialidad.setId(especialidadId);
                            especialidad.setNombre(rs.getString("especialidad_nombre"));
                            especialidad.setDescripcion(rs.getString("especialidad_descripcion"));
                            entity.getListaEspecialidades().add(especialidad);
                        }
                    } while (rs.next());
                }
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar profesional por ID: " + id);
        }
        return entity;
    }

    /**
     * Validar si existe profesional con el id
     *
     * @param id: identifiador del profesional
     * @return
     */
    public boolean existsById(Long id) {
        String existsSQL = "SELECT 1 FROM profesionales WHERE id = ?";
        try (Connection conn = this.personaDAO.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(existsSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al verificar existencia del profesional con ID: " + id);
            return false;
        }
    }

    @Override
    public List<Profesional> findAll() {
        List<Profesional> listaEntities = new ArrayList<>();
        String request = "SELECT * FROM profesionales";

        try (Connection conn = this.personaDAO.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request); ResultSet resultSet = sentence.executeQuery()) {

            while (resultSet.next()) {
                Profesional entity = (Profesional) this.cearEntity(resultSet);
                listaEntities.add(entity);
            }
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar todos los profesionales registrados");
        }
        return listaEntities;
    }

    @Override
    public Profesional update(Profesional entity) {
        String updateSQL = "UPDATE profesionales SET nombre=?, apellido=?, email=?, telefono=?, usuario_id=? WHERE id=?";

        try (Connection conn = this.personaDAO.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            this.setSentenceEntity(sentence, entity);
            sentence.setLong(6, entity.getId());

            sentence.executeUpdate();
        } catch (NullPointerException | SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar profesional ID: " + entity.getId());
            return null;
        }
        return this.findById(entity.getId());
    }

    public List<Profesional> findAllByParams(String parametro) {
        List<Profesional> listEntity = new ArrayList<>();
        String request = "SELECT * FROM profesionales WHERE nombre LIKE ? OR apellido LIKE ? OR email LIKE ?";

        try (Connection conn = this.personaDAO.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(request)) {
            String querySQL = "%" + parametro + "%";
            sentence.setString(1, querySQL);
            sentence.setString(2, querySQL);
            sentence.setString(3, querySQL);

            try (ResultSet resultSet = sentence.executeQuery()) {
                while (resultSet.next()) {
                    Profesional entity = (Profesional) cearEntity(resultSet);
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
        try (Connection conn = this.personaDAO.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(deleteSQL)) {
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

    private Persona cearEntity(final ResultSet resultSet) throws SQLException {
        String rolNombre = resultSet.getString("rol_nombre");
        Persona entity = switch (rolNombre) {
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

}
