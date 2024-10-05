package com.orozco.gesterin.DAO.Implementaciones;

import com.orozco.gesterin.DAO.ConnectionMysql;
import com.orozco.gesterin.DAO.GenericDAO;
import com.orozco.gesterin.exception.ControllerExceptionHandler;
import com.orozco.gesterin.model.AntecedentePersonal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author crist
 */
public class AntecedentePersonalDAO implements GenericDAO<AntecedentePersonal, Long> {

    private final ConnectionMysql connection;

    public AntecedentePersonalDAO() {
        this.connection = new ConnectionMysql();
    }

    @Override
    public boolean save(AntecedentePersonal entity) {
        String saveSQL = "INSERT INTO antecedentes_personales (observacion, tipoAntecedente_id, cliente_id) VALUES (?, ?, ?)";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(saveSQL, Statement.RETURN_GENERATED_KEYS)) {
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
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al registrar Antecedente Personal");
            return false;
        }
        return true;
    }

    @Override
    public AntecedentePersonal findById(Long id) {
        AntecedentePersonal antecedente = null;
        String findSQL = "SELECT * FROM antecedentes_personales WHERE id=?";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(findSQL)) {
            sentence.setLong(1, id);
            try (ResultSet rs = sentence.executeQuery()) {
                if (rs.next()) {
                    this.cearEntity(rs);
                }
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar Antecedente Personal por ID");
        }
        return antecedente;
    }

    public List<AntecedentePersonal> findAll(Long clienteId) {
        List<AntecedentePersonal> antecedentes = new ArrayList<>();
        String findAllSQL = "SELECT * FROM antecedentes_personales WHERE cliente_id=?";
        try (Connection conn = this.connection.getConn(); PreparedStatement stmt = conn.prepareStatement(findAllSQL)) {
            stmt.setLong(1, clienteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AntecedentePersonal antecedente = this.cearEntity(rs);
                    antecedentes.add(antecedente);
                }
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al buscar Antecedentes Personales por Cliente ID");
        }
        return antecedentes;
    }

    @Override
    public boolean update(AntecedentePersonal entity) {
        String updateSQL = "UPDATE antecedentes_personales SET observacion=?, tipoAntecedente_id=?, cliente_id=? WHERE id=?";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(updateSQL)) {
            sentence.setString(1, entity.getObservacion());
            sentence.setLong(2, entity.getTipoAntecedenteId());
            sentence.setLong(3, entity.getClienteId());
            sentence.setLong(4, entity.getId());

            if (sentence.executeUpdate() == 0) {
                throw new SQLException("No se ha modificadado ninguna fila.");
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al actualizar Antecedente Personal");
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        String deleteSQL = "DELETE FROM antecedentes_personales WHERE id=?";
        try (Connection conn = this.connection.getConn(); PreparedStatement sentence = conn.prepareStatement(deleteSQL)) {
            sentence.setLong(1, id);

            if (sentence.executeUpdate() == 0) {
                throw new SQLException("No se ha modificadado ninguna fila.");
            }
        } catch (SQLException ex) {
            ControllerExceptionHandler.handleError(ex, "Error al eliminar Antecedente Personal");
            return false;
        }
        return true;
    }

    private void setSentenceEntity(final PreparedStatement sentence, AntecedentePersonal entity) throws SQLException {
        sentence.setString(1, entity.getObservacion());
        sentence.setLong(2, entity.getTipoAntecedenteId());
        sentence.setLong(3, entity.getClienteId());
    }

    private AntecedentePersonal cearEntity(final ResultSet resultSet) throws SQLException {
        AntecedentePersonal entity = new AntecedentePersonal();
        entity.setId(resultSet.getLong("id"));
        entity.setObservacion(resultSet.getString("observacion"));
        entity.setTipoAntecedenteId(resultSet.getLong("tipoAntecedente_id"));
        entity.setClienteId(resultSet.getLong("cliente_id"));
        return entity;
    }

    @Override
    public List<AntecedentePersonal> findAll() {
        return null;
    }

}
