package com.team7.handsOnJava.repository;

import com.team7.handsOnJava.exception.EshopException;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T> {
    List<T> findAll();

    Optional<T> findByID(String id);

    void delete(T t) throws EshopException;

    void deleteByID(String id) throws EshopException;

    T create(T t) throws EshopException;

    List<T> createAll(T... ts) throws EshopException;

    boolean exists(T entity);
}
