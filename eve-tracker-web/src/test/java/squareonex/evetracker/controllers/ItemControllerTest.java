package squareonex.evetracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import squareonex.evetracker.services.ItemService;
import squareonex.evetrackerdata.model.Item;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ItemControllerTest {
    private final int maxItemPerPage = 10;
    ItemController itemController;
    @Mock
    ItemService itemService;
    @Mock
    Model model;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.itemController = new ItemController(itemService, maxItemPerPage);
    }

    @Test
    void listWithoutOptionalRequestParametersShouldUseDefaultValues() {
        Page<Item> expectedPage = Page.empty();
        when(itemService.findPaginated(any(Pageable.class))).thenReturn(expectedPage);

        String templateStr = itemController.list(model, Optional.empty(), Optional.empty());

        assertEquals("items/list", templateStr);
        verify(model, times(1)).addAttribute("data", expectedPage);
    }
}