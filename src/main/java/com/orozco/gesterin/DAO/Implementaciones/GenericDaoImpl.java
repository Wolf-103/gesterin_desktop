/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.ConnectionMysql;
import com.orozco.gesterin.DAO.GenericDAO;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.TipoBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @param <T> clase base de tipos
 * @param <ID> tipo de identificador
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public abstract class GenericDaoImpl<T extends TipoBase, ID> implements GenericDAO<T, ID> {

    protected ConnectionMysql connection;

    public GenericDaoImpl() {
        this.connection = new ConnectionMysql();
    }

    @Override
    public boolean save(T entity) {
        String saveSQL = "INSERT INTO " + getTableName() + " (nombre, descripcion) VALUES (?, ?)";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(saveSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            sentence.setString(1, entity.getNombre());
            sentence.setString(2, entity.getDescripcion());

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
            return false;
        }
        return true;
    }

    @Override
    public T findById(ID id) {
        String findSQL = "SELECT * FROM " + getTableName() + " WHERE id=?";
        T entity = null;
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findSQL)) {
            sentence.setLong(1, (Long) id);
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
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
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
    public boolean update(T entity) {
        String updateSQL = "UPDATE " + getTableName() + " SET nombre=?, descripcion=? WHERE id=?";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            sentence.setString(1, entity.getNombre());
            sentence.setString(2, entity.getDescripcion());
            sentence.setLong(3, entity.getId());
            sentence.executeUpdate();
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar " + getClassName() + " ID: " + entity.getId());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(ID id) {
        String deleteSQL = "DELETE FROM " + getTableName() + " WHERE id=?";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(deleteSQL)) {
            sentence.setLong(1, (Long) id);
            sentence.executeUpdate();
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al eliminar " + getClassName() + " ID: " + id);
            return false;
        }
        return true;
    }

    protected abstract String getTableName();
    protected abstract String getClassName();
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;
}
