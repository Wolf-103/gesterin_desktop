package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.Implementaciones.GenericDaoImpl;
import com.orozco.gesterin.model.Patologia;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class PatologiaDAO extends GenericDaoImpl<Patologia, Long>{

    @Override
    protected String getTableName() {
        return "patologias";
    }

    @Override
    protected String getClassName() {
        return "Tipo de Patología";
    }

    @Override
    protected Patologia mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Patologia(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));
    }
}
