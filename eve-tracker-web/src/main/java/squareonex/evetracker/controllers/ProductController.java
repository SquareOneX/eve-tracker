package squareonex.evetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import squareonex.evetrackerdata.services.ProductService;

@RequestMapping("products")
@Controller
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"", "index", "index.html"})
    public String list(Model model){
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }
}
