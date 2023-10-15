package squareonex.evetracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import squareonex.evetracker.services.BlueprintService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BlueprintControllerTest {
    BlueprintController blueprintController;
    @Mock
    BlueprintService blueprintService;
    @Mock
    Model modelMock;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.blueprintController = new BlueprintController(blueprintService);
    }

    @Test
    void list() {
        String list = blueprintController.list(modelMock);

        assertEquals("blueprints/list", list);
        verify(modelMock,times(1)).addAttribute("blueprints", blueprintService.getBlueprints());
    }
}