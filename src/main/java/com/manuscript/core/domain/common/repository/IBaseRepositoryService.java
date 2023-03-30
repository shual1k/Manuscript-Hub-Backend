package com.manuscript.core.domain.common.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBaseRepositoryService<M> {
    M save(M model) throws IllegalArgumentException;

    default List<M> saveAll(List<M> models) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    boolean existsById(UUID id) throws IllegalArgumentException;

    Optional<M> getById(UUID id) throws IllegalArgumentException;

    List<M> getAll();

    void deleteById(M model);

    void deleteAll();
}
