package squareonex.evetracker.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import squareonex.evetracker.services.BlueprintService;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintAction;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BlueprintControllerTest {
    private static final Long BLUEPRINT_ID = 0L;
    private static final Integer ACTIVITY_ID = 1;
    BlueprintController blueprintController;
    @Mock
    BlueprintService blueprintService;
    @Mock
    Model modelMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.blueprintController = new BlueprintController(blueprintService, 10);
    }

    @Test
    void list() {
        Page<Blueprint> expectedPage = Page.empty();
        when(blueprintService.findPaginated(any(Pageable.class))).thenReturn(expectedPage);

        String list = blueprintController.list(modelMock, Optional.empty(), Optional.empty());

        assertEquals("blueprints/list", list);
        verify(modelMock, times(1)).addAttribute("data", expectedPage);
    }

    @Test
    void showBlueprintByIds() {
        BlueprintAction blueprintAction = new BlueprintAction();
        doReturn(blueprintAction).when(blueprintService).findByBlueprintIdAndActivityId(BLUEPRINT_ID, ACTIVITY_ID);
        String templateStr = blueprintController.showBlueprintByIdAndActivity(BLUEPRINT_ID, ACTIVITY_ID, modelMock);

        assertEquals("blueprints/show", templateStr);
        verify(blueprintService, times(1)).findByBlueprintIdAndActivityId(BLUEPRINT_ID, ACTIVITY_ID);
        verify(modelMock, times(1)).addAttribute("blueprint", blueprintAction);
    }
}