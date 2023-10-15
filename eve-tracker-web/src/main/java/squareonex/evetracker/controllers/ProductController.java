package squareonex.evetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import squareonex.evetracker.services.ItemService;

@RequestMapping("products")
@Controller
public class ProductController {
    private final ItemService itemService;
    public ProductController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping({"", "index", "index.html"})
    public String list(Model model){
        model.addAttribute("items", itemService.findAll());
        return "products/list";
    }
}
