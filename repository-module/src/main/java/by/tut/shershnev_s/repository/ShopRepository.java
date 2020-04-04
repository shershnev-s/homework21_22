package by.tut.shershnev_s.repository;

import by.tut.shershnev_s.repository.model.Shop;

import java.util.List;

public interface ShopRepository extends GenericDao<Long, Shop> {

    List<Shop> findByLocation(String location);
}
