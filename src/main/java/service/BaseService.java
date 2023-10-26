package service;

import java.util.List;

public interface BaseService<E> {
    E save(E entity) throws Exception;

    List<E> findAll() throws Exception;

    E findById(Long id) throws Exception;

    boolean delete(Long id) throws Exception;

}
