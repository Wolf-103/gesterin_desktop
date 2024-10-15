package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.model.Especialidad;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class EspecialidadDAO extends GenericDaoImpl<Especialidad, Long>{

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
}
