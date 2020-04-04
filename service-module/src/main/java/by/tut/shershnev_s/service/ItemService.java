package by.tut.shershnev_s.service;

import by.tut.shershnev_s.service.model.ItemDTO;
import by.tut.shershnev_s.service.model.ItemWithShopDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {

    Long save(ItemDTO itemDTO);

    List<ItemDTO> findAll();

    ItemWithShopDTO findById(Long id);

    void deleteById(Long id);

    List<ItemDTO> findBySeveralParams(String name, BigDecimal priceMin, BigDecimal priceMax);

}
