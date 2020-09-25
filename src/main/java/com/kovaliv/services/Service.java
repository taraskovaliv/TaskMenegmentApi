package com.kovaliv.services;

import com.kovaliv.repos.Repo;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Service<T> {
    protected final Repo<T> repo;

    public void save(T t) {
        repo.save(t);
    }

    public void delete(T t) {
        repo.delete(t);
    }

    public void update(T t) {
        repo.update(t);
    }

    public T getById(Class<T> clazz, Integer id) {
        return repo.getById(clazz, id);
    }

    public List<T> getAll(Class<T> clazz) {
        return repo.getAll(clazz);
    }
}
