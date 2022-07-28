package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;

import java.util.List;
import java.util.Optional;

public interface AbstractService<T, ID> {
    List<T> findAll() throws EshopException;

    Optional<T> findByID(ID id) throws EshopException;

    void delete(T t) throws EshopException;

    T create(T t) throws EshopException;

    List<T> createAll(T... ts) throws EshopException;

}
