package by.tut.shershnev_s.web.controller;

import by.tut.shershnev_s.service.ShopService;
import by.tut.shershnev_s.service.model.ShopDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.List;

@Controller
@RequestMapping("/shops")
public class ShopController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/add_shop")
    public String addShop(@Valid @ModelAttribute(name = "shop") ShopDTO shop, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            logger.warn("Attempt to save " + shop.getName() + " is unsuccessfully");
            logger.warn(errors.getAllErrors());
            model.addAttribute("shop", shop);
            return "/add_shop";
        } else {
            shopService.add(shop);
        }
        return "redirect:/shops";
    }

    @GetMapping("/add_shop")
    public String getAddShop(Model model) {
        model.addAttribute("shop", new ShopDTO());
        return "add_shop";
    }

    @GetMapping
    public String getShops(Model model) {
        List<ShopDTO> shopDTOS = shopService.findAll();
        model.addAttribute("shops", shopDTOS);
        model.addAttribute("shops_by_location", new ShopDTO());
        return "shops";
    }

    @GetMapping("/search_shop")
    public String findShops(@ModelAttribute(name = "shops_by_location") ShopDTO shopDTO, Model model) {
        List<ShopDTO> shopDTOS = shopService.findByLocation(shopDTO.getLocation());
        model.addAttribute("shops_by_location", shopDTOS);
        return "search_shop";
    }
}
