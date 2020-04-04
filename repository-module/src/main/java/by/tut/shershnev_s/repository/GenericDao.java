package by.tut.shershnev_s.repository;

import java.util.List;

public interface GenericDao<I, T> {

    void add(T entity);

    List<T> findAll();

    T findById(I id);

    void deleteById(I id);

    T findByName(String value);


}
