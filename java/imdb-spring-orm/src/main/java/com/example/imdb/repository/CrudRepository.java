package com.example.imdb.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E,K> {
    Optional<E> findById(K id);
    List<E> finAll(int pageNo, int pageSize);
    E save(E e);
    E update(E e);
    Optional<E> removeById(K id);
    Optional<E> remove(E e);
}
