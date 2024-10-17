package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.model.TipoAntecedente;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class TipoAntecedentesDAO extends GenericDaoImpl<TipoAntecedente, Long>{

    @Override
    protected String getTableName() {
        return "tipos_antecedentes";
    }

    @Override
    protected String getClassName() {
        return "Tipo de Antecedente";
    }

    @Override
    protected TipoAntecedente mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new TipoAntecedente(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"));
    }
}
