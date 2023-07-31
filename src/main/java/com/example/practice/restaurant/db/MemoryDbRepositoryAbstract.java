package com.example.practice.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T> {

    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index).findFirst();
    }

    @Override
    public List<T> findAll() {
        return db;
    }

    @Override
    public T save(T entity) {
        var optionalEntity = db.stream()
                .filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if (optionalEntity.isEmpty()) {
            entity.setIndex(++index);
            db.add(entity);
        } else {
            var preIndex = optionalEntity.get().getIndex();
            entity.setIndex(preIndex);
            deleteById(preIndex);
            db.add(entity);
        }
        return entity;
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream()
                .filter(it -> it.getIndex() == index).findFirst();
        optionalEntity.ifPresent(db::remove);
    }

}
