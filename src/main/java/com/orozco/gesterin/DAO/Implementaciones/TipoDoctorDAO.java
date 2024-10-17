package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.model.TipoDoctor;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class TipoDoctorDAO extends GenericDaoImpl<TipoDoctor, Long>{

    @Override
    protected String getTableName() {
        return "tipos_doctores";
    }

    @Override
    protected String getClassName() {
        return "Tipo de Doctor";
    }

    @Override
    protected TipoDoctor mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new TipoDoctor(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));
    }
}
