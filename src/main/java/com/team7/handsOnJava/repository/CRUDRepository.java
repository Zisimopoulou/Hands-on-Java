package com.team7.handsOnJava.repository;

import com.team7.handsOnJava.exception.EshopException;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T, ID> {
    List<T> findAll() throws EshopException;

    Optional<T> findByID(ID id) throws EshopException;

    boolean delete(T t) throws EshopException;

    T create(T t) throws EshopException;

    List<T> createAll(T... ts) throws EshopException;

    boolean update(T t) throws EshopException;
}
