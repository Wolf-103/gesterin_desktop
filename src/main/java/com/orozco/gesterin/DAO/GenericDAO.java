package com.orozco.gesterin.DAO;

import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @param <T> tipo entidad
 * @param <ID> tipo de dato identificador
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public interface GenericDAO<T, ID> {

    T save(T entity);

    T findById(ID id);

    List<T> findAll();

    T update(T entity);

    boolean delete(ID id);
}
