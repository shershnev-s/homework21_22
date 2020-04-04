package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.ItemRepository;
import by.tut.shershnev_s.repository.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;


@Repository
public class ItemRepositoryImpl extends GenericDaoImpl<Long, Item> implements ItemRepository {

    @Override
    public List<Item> findBySeveralParams(String name, BigDecimal priceMin, BigDecimal priceMax) {
        String hql;
        Query query;
        if (priceMin == null && name.equals("")) {
            hql = "FROM " + entity.getSimpleName() + " i WHERE (i.itemDetails.price <=:priceMax)";
            query = entityManager.createQuery(hql);
            query.setParameter("priceMax", priceMax);
            return query.getResultList();
        } else if (priceMax == null && name.equals("")) {
            hql = "FROM " + entity.getSimpleName() + " i WHERE (i.itemDetails.price >=:priceMin)";
            query = entityManager.createQuery(hql);
            query.setParameter("priceMin", priceMin);
            return query.getResultList();
        } else if (priceMin != null && priceMax != null && !name.equals("")) {
            hql = "FROM " + entity.getSimpleName() + " i WHERE (i.itemDetails.price BETWEEN :priceMin AND " +
                    ":priceMax) AND (i.name =:name)";
            query = entityManager.createQuery(hql);
            query.setParameter("priceMin", priceMin);
            query.setParameter("priceMax", priceMax);
            query.setParameter("name", name);
            return query.getResultList();
        } else if (priceMin == null && priceMax == null) {
            hql = "FROM " + entity.getSimpleName() + " i WHERE (i.name=:name)";
            query = entityManager.createQuery(hql);
            query.setParameter("name", name);
            return query.getResultList();
        } else if (priceMax == null && !name.equals("")) {
            hql = "FROM " + entity.getSimpleName() + " i WHERE (i.itemDetails.price >=:priceMin AND i.name =:name)";
            query = entityManager.createQuery(hql);
            query.setParameter("priceMin", priceMin);
            query.setParameter("name", name);
            return query.getResultList();
        } else {
            hql = "FROM " + entity.getSimpleName() + " i WHERE (i.itemDetails.price BETWEEN :priceMin AND " +
                    ":priceMax)";
            query = entityManager.createQuery(hql);
            query.setParameter("priceMin", priceMin);
            query.setParameter("priceMax", priceMax);
            return query.getResultList();
        }
    }
}