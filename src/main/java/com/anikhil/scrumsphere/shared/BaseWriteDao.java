package com.anikhil.scrumsphere.shared;

public interface BaseWriteDao<T> {
    T save(T t);
    T update(T t);
    void delete(T t);
}
