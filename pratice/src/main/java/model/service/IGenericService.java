package model.service;

import java.util.List;

public interface IGenericService <T,ID>{
    List<T> findAll();
    boolean save(T t);
    T findById(ID id);
    void delete(ID id);
}
