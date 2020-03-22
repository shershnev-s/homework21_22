package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.ShopRepository;
import by.tut.shershnev_s.repository.model.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class ShopRepositoryImpl extends GenericDaoImpl<Long, Shop> implements ShopRepository {

    public ShopRepositoryImpl(){
        setEntity(Shop.class);
    }
}
