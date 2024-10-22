package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.Especialidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class EspecialidadDAO extends GenericDaoImpl<Especialidad, Long> {

    @Override
    protected String getTableName() {
        return "especialidades";
    }

    @Override
    protected String getClassName() {
        return "Tipo de Especialidad";
    }

    @Override
    protected Especialidad mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Especialidad(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));
    }

    public boolean addEspecialidadToProfesional(Long idProfesiona, Long idEspecialidad) {
        String saveSQL = "INSERT INTO profesional_especialidades (profesional_id, especialidad_id) VALUES (?, ?)";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(saveSQL, Statement.RETURN_GENERATED_KEYS)) {
            sentence.setLong(1, idProfesiona);
            sentence.setLong(2, idEspecialidad);

            if (sentence.executeUpdate() == 0) {
                throw new SQLException("No se ha modificadado ninguna fila.");
            }

            try (ResultSet generatedKeys = sentence.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Long id = (generatedKeys.getLong(1));
                } else {
                    throw new SQLException("No fue posible obtener el ID generado.");
                }
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al asignar especialidad a Profesional");
            return false;
        }
        return true;
    }
}
