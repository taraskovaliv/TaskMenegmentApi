package com.kovaliv.services;

import com.kovaliv.aspect.CountTime;
import com.kovaliv.repos.Repo;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Service<T> {
    protected final Repo<T> repo;

    @CountTime
    public T save(T t) {
        return repo.save(t);
    }

    @CountTime
    public void delete(T t) {
        repo.delete(t);
    }

    @CountTime
    public void update(T t) {
        repo.update(t);
    }

    @CountTime
    public T getById(Class<T> clazz, Integer id) {
        return repo.getById(clazz, id);
    }

    @CountTime
    public List<T> getAll(Class<T> clazz) {
        return repo.getAll(clazz);
    }
}
