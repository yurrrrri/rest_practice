package com.example.practice.restaurant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {

    Optional<T> findById(int index);
    List<T> findAll();
    T save(T entity);
    void deleteById(int index);
}
