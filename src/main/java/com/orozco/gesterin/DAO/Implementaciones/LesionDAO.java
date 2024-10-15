package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.ConnectionMysql;
import com.orozco.gesterin.DAO.GenericDAO;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.Lesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public abstract class LesionDAO implements GenericDAO<Lesion, Long> {
    
    protected ConnectionMysql connection;
    
    public LesionDAO() {
        this.connection = new ConnectionMysql();
    }
    
    @Override
    public Lesion save(Lesion entity) {
        String saveSQL = "INSERT INTO " + getTableName() + " (nombre, descripcion, extremidad) VALUES (?, ?, ?)";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(saveSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            sentence.setString(1, entity.getNombre());
            sentence.setString(2, entity.getDescripcion());
            sentence.setString(3, entity.getExtremidad());
            
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
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al registrar " + getClassName());
            return null;
        }
        return this.findById(entity.getId());
    }
    
    @Override
    public Lesion findById(Long id) {
        String findSQL = "SELECT * FROM " + getTableName() + " WHERE id=?";
        Lesion entity = null;
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                if (rs.next()) {
                    entity = mapResultSetToEntity(rs);
                }
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar " + getClassName() + " por ID: " + id);
        }
        return entity;
    }
    
    @Override
    public List<Lesion> findAll() {
        List<Lesion> entities = new ArrayList<>();
        String findAllSQL = "SELECT * FROM " + getTableName();
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findAllSQL); ResultSet rs = sentence.executeQuery()) {
            while (rs.next()) {
                entities.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar todas las entidades de tipo " + getClassName());
        }
        return entities;
    }
    
    @Override
    public Lesion update(Lesion entity) {
        String updateSQL = "UPDATE " + getTableName() + " SET nombre=?, descripcion=?, extremidad=? WHERE id=?";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            sentence.setString(1, entity.getNombre());
            sentence.setString(2, entity.getDescripcion());
            sentence.setString(3, entity.getExtremidad());
            
            sentence.setLong(4, entity.getId());
            sentence.executeUpdate();
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar " + getClassName() + " ID: " + entity.getId());
            return null;
        }
        return this.findById(entity.getId());
    }
    
    @Override
    public boolean delete(Long id) {
        String deleteSQL = "DELETE FROM " + getTableName() + " WHERE id=?";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(deleteSQL)) {
            sentence.setLong(1, id);
            sentence.executeUpdate();
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al eliminar " + getClassName() + " ID: " + id);
            return false;
        }
        return true;
    }
    
    protected abstract String getTableName();
    
    protected abstract String getClassName();
    
    protected abstract Lesion mapResultSetToEntity(ResultSet rs) throws SQLException;
}
