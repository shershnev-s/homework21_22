package by.tut.shershnev_s.web.controller;

import by.tut.shershnev_s.service.ItemService;
import by.tut.shershnev_s.service.ShopService;
import by.tut.shershnev_s.service.model.ItemDTO;
import by.tut.shershnev_s.service.model.ItemWithShopDTO;
import by.tut.shershnev_s.service.model.ShopDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ShopService shopService;
    private final ItemService itemService;

    public ItemController(ShopService shopService, ItemService itemService) {
        this.shopService = shopService;
        this.itemService = itemService;
    }

    @GetMapping("/add_item")
    public String getAddItems(Model model) {
        List<ShopDTO> shopDTOS = shopService.findAll();
        model.addAttribute("item", new ItemDTO());
        model.addAttribute("shops", shopDTOS);
        return "add_item";
    }

    @PostMapping("/add_item")
    public String addItem(@Valid @ModelAttribute(name = "item") ItemDTO item, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            List<ShopDTO> shopDTOS = shopService.findAll();
            model.addAttribute("item", item);
            model.addAttribute("shops", shopDTOS);
            logger.warn("Attempt to save " + item.getName() + " is unsuccessfully");
            logger.warn(errors.getAllErrors());
            return "add_item";
        } else {
            itemService.save(item);
        }
        return "redirect:/items";
    }

    @GetMapping
    public String items(Model model) {
        List<ItemDTO> items = itemService.findAll();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/{id}")
    public String openItem(@PathVariable Long id, Model model) {
        ItemWithShopDTO itemWithShopDTO = itemService.findById(id);
        model.addAttribute("item", itemWithShopDTO);
        return "item";
    }

    @GetMapping("/{id}/remove")
    public String deleteItem(@PathVariable long id) {
        itemService.deleteById(id);
        return "redirect:/items";
    }

    @GetMapping("/delete")
    public String getDelete(Model model) {
        List<ItemDTO> items = itemService.findAll();
        model.addAttribute("items", items);
        return "delete_item";
    }

    @GetMapping("/item")
    public String getShopWithItemById(Model model) {
        return "item";
    }


}
