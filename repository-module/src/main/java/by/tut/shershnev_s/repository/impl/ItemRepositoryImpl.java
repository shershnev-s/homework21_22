package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.ItemRepository;
import by.tut.shershnev_s.repository.model.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends GenericDaoImpl<Long, Item> implements ItemRepository {

}
