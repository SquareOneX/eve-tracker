package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Item;
import squareonex.evetrackerdata.model.ids.BlueprintId;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class BlueprintServiceImplTest {
    private static final Long BLUEPRINT_ID = 0L;
    private static final Integer ACTIVITY_ID = 1;
    @InjectMocks BlueprintServiceImpl blueprintService;
    @Mock
    BlueprintRepository blueprintRepositoryMock;
    @Mock
    ItemRepository itemRepositoryMock;
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
        Item item = new Item(BLUEPRINT_ID, null);
        Activity activity = new Activity();
        activity.setId(ACTIVITY_ID);

        when(itemRepositoryMock.findById(BLUEPRINT_ID)).thenReturn(Optional.of(item));
        when(activityRepositoryMock.findById(ACTIVITY_ID)).thenReturn(Optional.of(activity));

        Blueprint blueprintIdAndActivityId = blueprintService.findByBlueprintIdAndActivityId(BLUEPRINT_ID, ACTIVITY_ID);

        BlueprintId blueprintId = new BlueprintId(item, activity);
        verify(activityRepositoryMock, times(1)).findById(ACTIVITY_ID);
        verify(itemRepositoryMock, times(1)).findById(BLUEPRINT_ID);
        verify(blueprintRepositoryMock, times(1)).findById(blueprintId);
    }

    @Test
    void findByIdShouldReturnBlueprintOrNull() {
        BlueprintId id = new BlueprintId();
        assertNull(blueprintService.findById(id));

        verify(blueprintRepositoryMock, times(1)).findById(any(BlueprintId.class));
    }
}