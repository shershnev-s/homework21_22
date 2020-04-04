package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.GenericDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public abstract class GenericDaoImpl<I, T> implements GenericDao<I, T> {

    protected Class<T> entity;

    @PersistenceContext
    protected EntityManager entityManager;

    protected GenericDaoImpl() {
        ParameterizedType generalSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entity = (Class<T>) generalSuperclass.getActualTypeArguments()[1];
    }


    @Override
    public void add(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("from " + entity.getName())
                .getResultList();
    }

    @Override
    public T findById(I id) {
        return entityManager.find(entity, id);
    }

    @Override
    public void deleteById(I id) {
        entityManager.remove(entityManager.find(entity, id));
    }

    @Override
    public T findByName(String name) {
        String hql = "FROM " + entity.getSimpleName() + " WHERE username LIKE '" + name + "'";
        Query query = entityManager.createQuery(hql);
        return (T) query.getSingleResult();
    }

}
