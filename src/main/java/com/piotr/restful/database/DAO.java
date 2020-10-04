package com.piotr.restful.database;

import javax.ejb.Local;
import java.util.List;

@Local
public interface DAO<T> {

    public List getAll();

    public T findById(int id);

    public void update(T t);

    public void create(T t);

    public void delete(T t);

}
