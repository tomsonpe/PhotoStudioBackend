package com.photostudio.services.customers;

import java.util.Set;

/**
 * Created by admin on 2016/08/20.
 */
public interface Services<E,ID> {
    E create(E entity);

    E readById(ID id);

    Set<E> readAll();

    E update(E entity);

    void delete(E entity);
}
