package by.tut.shershnev_s.repository;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<I, T> {

    void add(T entity);

    List<T> findAll();

}
