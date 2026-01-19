package com.research.repository;

import com.research.exception.DuplicateIdException;
import com.research.exception.NotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseRepository<T extends Identifiable> {

    protected final Map<Integer, T> storage = new HashMap<>();

    // ---------------- ADD ----------------
    public void add(T entity) {
        if (storage.containsKey(entity.getId())) {
            throw new DuplicateIdException(
                    "Entity with ID " + entity.getId() + " already exists."
            );
        }
        storage.put(entity.getId(), entity);
    }

    // ---------------- GET ----------------
    public T getById(int id) {
        T entity = storage.get(id);
        if (entity == null) {
            throw new NotFoundException("Entity with ID " + id + " not found.");
        }
        return entity;
    }

    // ---------------- GET ALL ----------------
    public Collection<T> getAll() {
        return storage.values();
    }

    // ---------------- UPDATE ----------------
    public void update(T entity) {
        if (!storage.containsKey(entity.getId())) {
            throw new NotFoundException(
                    "Cannot update. Entity with ID " + entity.getId() + " not found."
            );
        }
        storage.put(entity.getId(), entity);
    }

    // ---------------- DELETE ----------------
    public void delete(int id) {
        if (!storage.containsKey(id)) {
            throw new NotFoundException(
                    "Cannot delete. Entity with ID " + id + " not found."
            );
        }
        storage.remove(id);
    }

    // ---------------- EXISTS ----------------
    public boolean existsById(int id) {
        return storage.containsKey(id);
    }

    // ---------------- COUNT ----------------
    public int count() {
        return storage.size();
    }
}
