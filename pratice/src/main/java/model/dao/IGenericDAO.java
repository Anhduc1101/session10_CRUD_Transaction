package model.dao;

import java.util.List;

public interface IGenericDAO <T,ID>{
    List<T> findAll();
    boolean save(T t);
    T findById(ID id);
    void delete(ID id);
}
