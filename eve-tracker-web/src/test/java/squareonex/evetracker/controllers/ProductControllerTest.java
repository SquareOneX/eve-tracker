package squareonex.evetracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import squareonex.evetracker.services.ItemService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ProductControllerTest {
    ProductController productController;
    @Mock
    ItemService productService;
    @Mock
    Model model;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.productController = new ProductController(productService);
    }

    @Test
    void list() {
        String templateStr = productController.list(model);

        assertEquals("products/list", templateStr);
        verify(model, times(1)).addAttribute("items", productService.findAll());
    }
}