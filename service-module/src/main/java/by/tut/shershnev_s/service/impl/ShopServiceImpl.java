package by.tut.shershnev_s.service.impl;

import by.tut.shershnev_s.repository.ShopRepository;
import by.tut.shershnev_s.repository.model.Shop;
import by.tut.shershnev_s.service.ShopService;
import by.tut.shershnev_s.service.model.ShopDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    @Transactional
    public void add(ShopDTO shopDTO) {
        Shop shop = getObjectFromDTO(shopDTO);
        shopRepository.add(shop);
    }

    @Override
    @Transactional
    public List<ShopDTO> findAll() {
        List<ShopDTO> shopDTOS = new ArrayList<>();
        List<Shop> shops = shopRepository.findAll();
        for (Shop shop : shops) {
            ShopDTO shopDTO = convertToDTO(shop);
            shopDTOS.add(shopDTO);
        }
        return shopDTOS;
    }

    private ShopDTO convertToDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setName(shop.getName());
        shopDTO.setLocation(shop.getLocation());
        return shopDTO;
    }

    private Shop getObjectFromDTO(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setName(shopDTO.getName());
        shop.setLocation(shopDTO.getLocation());
        return shop;
    }
}
