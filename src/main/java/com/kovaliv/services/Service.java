package com.kovaliv.services;

import com.kovaliv.repos.Repo;

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
}
