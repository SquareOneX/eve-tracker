package squareonex.evetracker.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import squareonex.evetracker.services.ItemService;
import squareonex.evetrackerdata.model.Item;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerIT {
    @Autowired
    ItemController itemController;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ItemService itemService;
    private final int pageSize = 10;

    @Test
    void webPageShouldLoadWithExpectedContent() throws Exception {
        int pageNumber = 1;
        Page<Item> expected = itemService.findPaginated(PageRequest.of(pageNumber - 1, pageSize)); //internally 0-indexed
        mockMvc.perform(get("/items?page=" + pageNumber + "&size=" + pageSize))
                .andExpect(status().isOk())
                .andExpect(model().attribute("data", expected));
    }
}