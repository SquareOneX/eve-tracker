package squareonex.evetracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.plugins.MockMaker;
import org.springframework.ui.Model;
import squareonex.evetrackerdata.services.BlueprintService;

import javax.lang.model.util.Types;

import static org.junit.jupiter.api.Assertions.*;
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
        verify(modelMock,times(1)).addAttribute("blueprints", blueprintService.findAll());
    }
}