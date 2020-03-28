package by.tut.shershnev_s.service.impl;

import by.tut.shershnev_s.repository.ItemDetailsRepository;
import by.tut.shershnev_s.repository.model.Item;
import by.tut.shershnev_s.repository.model.ItemDetails;
import by.tut.shershnev_s.service.ItemDetailsService;
import by.tut.shershnev_s.service.model.ItemDetailsDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemDetailsServiceImpl implements ItemDetailsService {

    private final ItemDetailsRepository itemDetailsRepository;

    public ItemDetailsServiceImpl(ItemDetailsRepository itemDetailsRepository) {
        this.itemDetailsRepository = itemDetailsRepository;
    }

    @Override
    @Transactional
    public void add(ItemDetailsDTO itemDetailsDTO) {
        ItemDetails itemDetails = getItemDetailsFromDTO(itemDetailsDTO);
        Item item = new Item();
        itemDetails.setItem(item);
        itemDetailsRepository.add(itemDetails);
    }

    private ItemDetails getItemDetailsFromDTO(ItemDetailsDTO itemDetailsDTO){
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setItemId(itemDetailsDTO.getItemId());
        itemDetails.setPrice(itemDetailsDTO.getPrice());
        return itemDetails;
    }
}
