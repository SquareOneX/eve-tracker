package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetracker.converters.BlueprintCopyToBlueprintCopyCommand;
import squareonex.evetracker.converters.ItemCommandToItem;
import squareonex.evetrackerdata.model.*;
import squareonex.evetrackerdata.model.ids.BlueprintActionId;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlueprintServiceImplTest {
    private static final Long BLUEPRINT_ID = 0L;
    private static final Integer ACTIVITY_ID = 1;
    BlueprintServiceImpl blueprintService;
    @Mock
    BlueprintRepository blueprintRepositoryMock;
    @Mock
    ActivityRepository activityRepositoryMock;
    @Mock
    ItemRepository itemRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.blueprintService = new BlueprintServiceImpl(
                blueprintRepositoryMock,
                itemRepositoryMock,
                new ItemCommandToItem(),
                new BlueprintCopyToBlueprintCopyCommand()
        );
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

    @Test
    void findBlueprintCopiesByProduct() {
        Blueprint blueprint = new Blueprint();
        BlueprintCopy bpc1 = new BlueprintCopy(blueprint, 100, 50_000F);
        bpc1.setId(0L);
        BlueprintCopy bpc2 = new BlueprintCopy(blueprint, 100, 50_000F);
        bpc2.setId(1L);
        blueprint.getCopies().add(bpc1);
        blueprint.getCopies().add(bpc2);
        BlueprintAction blueprintAction = new BlueprintAction();
        blueprintAction.setBlueprint(blueprint);
        BlueprintProduct blueprintProduct = new BlueprintProduct();
        blueprintProduct.setBlueprintAction(blueprintAction);
        blueprintAction.getProducts().add(blueprintProduct);

        Item item = new Item(0L, "Item");
        blueprintProduct.setProduct(item);
        item.getBlueprints().add(blueprintProduct);

        Set<BlueprintCopy> copies = blueprintService.findBlueprintCopiesByItem(item);

        assertTrue(copies.contains(bpc1));
        assertTrue(copies.contains(bpc2));
    }

    @Test
    void findBlueprintCopyCommandsByItemCommand() {
        final long itemId = 0L;
        ItemCommand itemCommand = new ItemCommand();
        itemCommand.setId(itemId);

        when(itemRepositoryMock.findById(eq(itemId))).thenReturn(Optional.of(new Item(itemId, null)));

        blueprintService.findBlueprintCopyCommandsByItemCommand(itemCommand);
        verify(itemRepositoryMock).findById(eq(itemId));
    }

    @Test
    void findPaginated() {
    }
}