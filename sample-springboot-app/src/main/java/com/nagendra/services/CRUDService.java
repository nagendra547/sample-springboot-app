package com.nagendra.services;

import java.util.List;

/**
 * 
 * @author nagendra
 *
 * @param <T>
 */
public interface CRUDService<T> {
    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
