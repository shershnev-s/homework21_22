package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.GenericDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Repository
public abstract class GenericDaoImpl<I, T> implements GenericDao<I, T> {

    private Class<T> entity;

    @PersistenceContext
    private EntityManager entityManager;

    public final void setEntity(Class<T> entity) {
        this.entity = entity;
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

}
