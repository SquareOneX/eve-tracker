package squareonex.evetracker.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import squareonex.evetracker.services.BlueprintService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlueprintControllerIT {
    @Autowired
    BlueprintController blueprintController;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    BlueprintService blueprintService;
    @Test
    void webPageShouldLoad() throws Exception {
        mockMvc.perform(get("/blueprints"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("blueprints", blueprintService.getBlueprints()));
    }
}