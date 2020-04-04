package by.tut.shershnev_s.service.impl;

import by.tut.shershnev_s.repository.ItemDetailsRepository;
import by.tut.shershnev_s.repository.ItemRepository;
import by.tut.shershnev_s.repository.ShopRepository;
import by.tut.shershnev_s.repository.model.Item;
import by.tut.shershnev_s.repository.model.ItemDetails;
import by.tut.shershnev_s.repository.model.Shop;
import by.tut.shershnev_s.service.ItemService;
import by.tut.shershnev_s.service.model.ItemDTO;
import by.tut.shershnev_s.service.model.ItemDetailsDTO;
import by.tut.shershnev_s.service.model.ItemWithShopDTO;
import by.tut.shershnev_s.service.model.ShopDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemDetailsRepository itemDetailsRepository;
    private final ShopRepository shopRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ItemDetailsRepository itemDetailsRepository, ShopRepository shopRepository) {
        this.itemRepository = itemRepository;
        this.itemDetailsRepository = itemDetailsRepository;
        this.shopRepository = shopRepository;
    }

    @Override
    @Transactional
    public Long save(ItemDTO itemDTO) {
        Item item = getItemFromDTO(itemDTO);
        Shop shop = shopRepository.findById(itemDTO.getShopId());
        item.getShops().add(shop);
        itemRepository.add(item);
        ItemDetails itemDetails = getItemDetailsFromDTO(itemDTO);
        itemDetails.setItemId(item.getId());
        itemDetails.setItem(item);
        itemDetailsRepository.add(itemDetails);
        return item.getId();
    }

    @Override
    @Transactional
    public List<ItemDTO> findAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            ItemDTO itemDTO = convertItemsToDTO(item);
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    @Override
    @Transactional
    public ItemWithShopDTO findById(Long id) {
        Item item = itemRepository.findById(id);
        List<Shop> shops = item.getShops();
        List<ShopDTO> shopDTOS = new ArrayList<>();
        for (Shop shop : shops) {
            ShopDTO shopDTO = convertShopToDTO(shop);
            shopDTOS.add(shopDTO);
        }
        return convertToItemWithShopDTO(item, shopDTOS);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<ItemDTO> findBySeveralParams(String name, BigDecimal priceMin, BigDecimal priceMax) {
        List<Item> items = itemRepository.findBySeveralParams(name, priceMin, priceMax);
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : items) {
            ItemDTO itemDTO = convertItemsToDTO(item);
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    private ItemWithShopDTO convertToItemWithShopDTO(Item item, List<ShopDTO> shopDTOS) {
        ItemWithShopDTO itemWithShopDTO = new ItemWithShopDTO();
        itemWithShopDTO.setId(item.getId());
        itemWithShopDTO.setName(item.getName());
        itemWithShopDTO.setDescription(item.getDescription());
        ItemDetailsDTO itemDetailsDTO = convertDetailsToDTO(item.getItemDetails());
        itemWithShopDTO.setItemDetails(itemDetailsDTO);
        for (ShopDTO shopDTO : shopDTOS) {
            itemWithShopDTO.getShops().add(shopDTO);
        }
        return itemWithShopDTO;
    }

    private ItemDetailsDTO convertDetailsToDTO(ItemDetails itemDetails) {
        ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();
        itemDetailsDTO.setPrice(itemDetails.getPrice());
        return itemDetailsDTO;
    }

    private ShopDTO convertShopToDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setName(shop.getName());
        shopDTO.setLocation(shop.getLocation());
        return shopDTO;
    }

    private Item getItemFromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        return item;
    }

    private ItemDetails getItemDetailsFromDTO(ItemDTO itemDTO) {
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setPrice(itemDTO.getPrice());
        return itemDetails;
    }

    private ItemDTO convertItemsToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getItemDetails().getPrice());
        return itemDTO;
    }

}
