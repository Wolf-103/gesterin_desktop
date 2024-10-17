package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.model.Rol;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class RolDAO extends GenericDaoImpl<Rol, Long>{

    @Override
    protected String getTableName() {
        return "roles";
    }

    @Override
    protected String getClassName() {
        return "Rol";
    }

    @Override
    protected Rol mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Rol(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));
    }
}
