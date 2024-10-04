package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.Implementaciones.GenericDaoImpl;
import com.orozco.gesterin.model.TipoTratamiento;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class TipoTratamientoDAO extends GenericDaoImpl<TipoTratamiento, Long>{

    @Override
    protected String getTableName() {
        return "tipos_tratamientos";
    }

    @Override
    protected String getClassName() {
        return "Tipo de Tratamiento";
    }

    @Override
    protected TipoTratamiento mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new TipoTratamiento(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));
    }
}
