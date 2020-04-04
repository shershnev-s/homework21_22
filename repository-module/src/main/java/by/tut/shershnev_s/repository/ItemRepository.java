package by.tut.shershnev_s.repository;

import by.tut.shershnev_s.repository.model.Item;

import java.math.BigDecimal;
import java.util.List;


public interface ItemRepository extends GenericDao<Long, Item> {

    List<Item> findBySeveralParams(String name, BigDecimal priceMin, BigDecimal priceMax);

}
