package by.tut.shershnev_s.service;

import by.tut.shershnev_s.service.model.ShopDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShopService {

    void add(ShopDTO shopDTO);

    List<ShopDTO> findAll();
}
