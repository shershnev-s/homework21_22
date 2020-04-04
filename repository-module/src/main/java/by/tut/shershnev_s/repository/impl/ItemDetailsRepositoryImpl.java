package by.tut.shershnev_s.repository.impl;

import by.tut.shershnev_s.repository.ItemDetailsRepository;
import by.tut.shershnev_s.repository.model.ItemDetails;
import org.springframework.stereotype.Repository;


@Repository
public class ItemDetailsRepositoryImpl extends GenericDaoImpl<Long, ItemDetails> implements ItemDetailsRepository {

}
