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
    public String addDocs(@Valid @ModelAttribute(name = "shop") ShopDTO shop, BindingResult errors, Model model) {
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
    public String getAddDoc(Model model) {
        model.addAttribute("shop", new ShopDTO());
        return "add_shop";
    }

    @GetMapping
    public String getDocs(Model model) {
        List<ShopDTO> shopDTOS = shopService.findAll();
        model.addAttribute("shops", shopDTOS);
        return "shops";
    }

}
