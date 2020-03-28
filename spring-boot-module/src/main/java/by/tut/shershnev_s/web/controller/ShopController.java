package by.tut.shershnev_s.web.controller;

import by.tut.shershnev_s.service.ItemDetailsService;
import by.tut.shershnev_s.service.ItemService;
import by.tut.shershnev_s.service.ShopService;
import by.tut.shershnev_s.service.model.ItemAndDetailsDTO;
import by.tut.shershnev_s.service.model.ItemDTO;
import by.tut.shershnev_s.service.model.ItemDetailsDTO;
import by.tut.shershnev_s.service.model.ShopDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ShopController {

    private final ShopService shopService;
    private final ItemService itemService;
    private final ItemDetailsService itemDetailsService;

    public ShopController(ShopService shopService, ItemService itemService, ItemDetailsService itemDetailsService) {
        this.shopService = shopService;
        this.itemService = itemService;
        this.itemDetailsService = itemDetailsService;
    }

    @PostMapping("/add_shop")
    public String addDocs(@Valid @ModelAttribute(name = "shop") ShopDTO shop, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("shop", shop);
            return "/add_shop";
        } else {
            shopService.add(shop);
        }
        return "redirect:/add_shop";
    }

    @GetMapping("/add_shop")
    public String getAddDoc(Model model) {
        model.addAttribute("shop", new ShopDTO());
        return "add_shop";
    }

    @GetMapping("/shops")
    public String getDocs(Model model) {
        List<ShopDTO> shopDTOS = shopService.findAll();
        model.addAttribute("shops", shopDTOS);
        return "shops";
    }

    @GetMapping("/add_item")
    public String getAddItems(Model model) {
        List<ShopDTO> shopDTOS = shopService.findAll();
        model.addAttribute("item", new ItemAndDetailsDTO());
        return "add_item";
    }

    @PostMapping("/add_item")
    public String addItem(@ModelAttribute(name = "item") ItemAndDetailsDTO itemAndDetailsDTO, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("item", new ItemAndDetailsDTO());
            return "/add_item";
        } else {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setName(itemAndDetailsDTO.getName());
            itemDTO.setDescription(itemAndDetailsDTO.getDescription());
            ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();
            //itemDetailsDTO.setItemId(id);
            itemDetailsDTO.setPrice(itemAndDetailsDTO.getPrice());
            Long id = itemService.save(itemDTO,itemDetailsDTO);
            itemDetailsService.add(itemDetailsDTO);
        }
        return "add_item";
    }


}
