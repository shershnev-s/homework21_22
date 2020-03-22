package by.tut.shershnev_s.service.impl;

import by.tut.shershnev_s.repository.ItemRepository;
import by.tut.shershnev_s.repository.model.Item;
import by.tut.shershnev_s.repository.model.ItemDetails;
import by.tut.shershnev_s.service.ItemService;
import by.tut.shershnev_s.service.model.ItemDTO;
import by.tut.shershnev_s.service.model.ItemDetailsDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public Long save(ItemDTO itemDTO, ItemDetailsDTO itemDetailsDTO) {
        Item item = getObjectFromDTO(itemDTO);
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setItem(item);
        itemDetails.setPrice(itemDetailsDTO.getPrice());
        item.setItemDetails(itemDetails);
        itemRepository.add(item);
        return item.getId();
    }

    private ItemDTO convertToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        return itemDTO;
    }

    private Item getObjectFromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        return item;
    }
}
