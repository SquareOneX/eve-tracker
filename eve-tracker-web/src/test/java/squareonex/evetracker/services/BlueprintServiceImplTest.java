package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintAction;
import squareonex.evetrackerdata.model.ids.BlueprintActionId;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlueprintServiceImplTest {
    private static final Long BLUEPRINT_ID = 0L;
    private static final Integer ACTIVITY_ID = 1;
    @InjectMocks BlueprintServiceImpl blueprintService;
    @Mock
    BlueprintRepository blueprintRepositoryMock;
    @Mock
    ActivityRepository activityRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBlueprints() {
        blueprintService.getBlueprints();

        verify(blueprintRepositoryMock, times(1)).findAll();
    }

    @Test
    void findByBlueprintIdAndActivityIdShouldReturnExpectedValue() {
        Blueprint blueprint = new Blueprint(BLUEPRINT_ID, null);
        Activity activity = new Activity();
        activity.setId(ACTIVITY_ID);
        BlueprintAction action = new BlueprintAction(new BlueprintActionId(blueprint, activity));
        blueprint.getActions().add(action);

        when(blueprintRepositoryMock.findById(BLUEPRINT_ID)).thenReturn(Optional.of(blueprint));

        BlueprintAction blueprintIdAndActivityId = blueprintService.findByBlueprintIdAndActivityId(BLUEPRINT_ID, ACTIVITY_ID);

        verify(blueprintRepositoryMock, times(1)).findById(BLUEPRINT_ID);
        assertEquals(action, blueprintIdAndActivityId);
    }

    @Test
    void findByIdShouldReturnBlueprintOrNull() {
        assertNull(blueprintService.findById(BLUEPRINT_ID));

        verify(blueprintRepositoryMock, times(1)).findById(anyLong());
    }

    @Test
    void getBlueprintActions() {
        assertNotNull(blueprintService.getBlueprintActions());

        verify(blueprintRepositoryMock, times(1)).findAll();
    }
}