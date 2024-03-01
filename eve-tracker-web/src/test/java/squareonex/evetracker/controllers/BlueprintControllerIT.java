package squareonex.evetracker.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import squareonex.evetracker.services.BlueprintService;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintAction;
import squareonex.evetrackerdata.model.ids.BlueprintActionId;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
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
        Page<Blueprint> expected = blueprintService.findPaginated(PageRequest.of(0, 10));
        mockMvc.perform(get("/blueprints?page=1&size=10"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("data", expected));
    }

    @Test
    @Transactional
    void showBlueprintShouldLoad() throws Exception {
        BlueprintAction blueprintAction = blueprintService.getBlueprints().iterator().next().getActions().iterator().next();
        assertNotNull(blueprintAction);
        Long blueprintId = blueprintAction.getBlueprint().getId();
        Integer activityId = blueprintAction.getActivity().getId();
        //I know this is hideous code but this is only a test :)

        mockMvc.perform(get("/blueprints/" + blueprintId + "/" + activityId + "/show"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("blueprint", blueprintAction));
    }

    @Test
    void showBlueprintByIdShouldThrow404NotFound() throws Exception {
        mockMvc.perform(get("/blueprints/101/1/show"))
                .andExpect(status().isNotFound());
    }

}