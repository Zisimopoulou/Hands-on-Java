package com.team7.handsOnJava.service;

import com.team7.handsOnJava.exception.EshopException;
import com.team7.handsOnJava.model.BaseModel;
import com.team7.handsOnJava.repository.CRUDRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {
    public abstract CRUDRepository<T> getRepository();
    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }
    @Override
    public T create(final T entity) throws EshopException {
        return getRepository().create(entity);
    }
    @Override
    public List<T> createAll(final T... entities) throws EshopException {
        return getRepository().createAll(entities);
    }
    @Override
    public List<T> createAll(final List<T> entities) throws EshopException {
        return getRepository().createAll();
    }
    @Override
    public void delete(T entity) throws EshopException {
        getRepository().delete(entity);
    }

    @Override
    public void deleteById(String id) throws EshopException {
        getRepository().deleteByID(id);
    }
    @Override
    public boolean exists(T entity) throws EshopException {
        return getRepository().exists(entity);
    }

}
