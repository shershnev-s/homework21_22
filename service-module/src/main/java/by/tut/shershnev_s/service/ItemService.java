package by.tut.shershnev_s.service;

import by.tut.shershnev_s.repository.model.ItemDetails;
import by.tut.shershnev_s.service.model.ItemDTO;
import by.tut.shershnev_s.service.model.ItemDetailsDTO;

public interface ItemService {

    Long save(ItemDTO itemDTO, ItemDetailsDTO itemDetailsDTO);
}
