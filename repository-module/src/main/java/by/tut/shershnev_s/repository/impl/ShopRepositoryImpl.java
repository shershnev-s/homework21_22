package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.ShopRepository;
import by.tut.shershnev_s.repository.model.Shop;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ShopRepositoryImpl extends GenericDaoImpl<Long, Shop> implements ShopRepository {

    @Override
    public List<Shop> findByLocation(String location) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> query = cb.createQuery(Shop.class);
        Root<Shop> root = query.from(Shop.class);
        ParameterExpression<String> parameterExpression = cb.parameter(String.class);
        query.select(root).where(cb.equal(root.get("location"), parameterExpression));
        TypedQuery<Shop> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(parameterExpression, location);
        return typedQuery.getResultList();
    }
}
