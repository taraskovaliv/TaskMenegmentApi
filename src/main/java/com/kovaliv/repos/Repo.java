package com.kovaliv.repos;

public interface Repo<T> {
    T getById(Integer id);

    void save(T toSave);
}
