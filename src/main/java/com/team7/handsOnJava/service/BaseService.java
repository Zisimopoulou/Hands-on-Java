package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.BaseModel;

import java.util.List;

public interface BaseService<T extends BaseModel> {
    T create(final T entity) throws EshopException;

    List<T> createAll(final T... entities) throws EshopException;

    List<T> createAll(final List<T> entities) throws EshopException;

    void delete(T entity) throws EshopException;

    void deleteById(String id) throws EshopException;

    boolean exists(T entity) throws EshopException;

    List<T> findAll();
}