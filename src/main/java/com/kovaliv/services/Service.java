package com.kovaliv.services;

import com.kovaliv.repos.Repo;

import java.util.List;

public class Service<T> {
    protected Repo<T> repo;

    public Service() {
        repo = new Repo<>();
    }

    public void save(T t) {
        repo.save(t);
    }

    public void delete(T t) {
        repo.delete(t);
    }

    public T getById(Class<T> clazz, Integer id) {
        return repo.getById(clazz, id);
    }

    public List<T> getAll(Class<T> clazz) {
        return repo.getAll(clazz);
    }
}
