package by.tut.shershnev_s.service;

import by.tut.shershnev_s.repository.model.Item;
import by.tut.shershnev_s.repository.model.ItemDetails;
import by.tut.shershnev_s.service.model.ItemDetailsDTO;

public interface ItemDetailsService {

    void add(ItemDetailsDTO itemDetailsDTO);
}
