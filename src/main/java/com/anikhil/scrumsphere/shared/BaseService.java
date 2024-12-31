package com.anikhil.scrumsphere.shared;

public interface BaseService<T> {

    T create(T t);
    T saveUpdate(T t);
}
