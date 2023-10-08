package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Item;

import javax.naming.OperationNotSupportedException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class CopyServiceImplTest {
    CopyServiceImpl copyService;
    @Mock
    Blueprint blueprint;
    @Mock
    Item itemMock;
    private Activity activity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.copyService = new CopyServiceImpl();
        when(blueprint.getItemInfo()).thenReturn(itemMock);

        this.activity = new Activity();
        activity = new Activity();
        activity.setName("Copying");
        when(blueprint.getActivity()).thenReturn(activity);
    }

    @Test
    void copyCostShouldEqualOnePercentOfBPOCost() {
        //arrange
        float bpoCost = 5.0E07f;
        when(itemMock.getAvgCost()).thenReturn(bpoCost);

        //act
        Float cost = copyService.calculateCostPerCopy(blueprint);
        assertEquals(bpoCost / 100, cost);
    }

    @Test
    void copyingBlueprintShouldOnlyWorkForActivityCopying(){
        assertDoesNotThrow(() -> copyService.calculateCostPerCopy(blueprint));

        activity.setName("Invention");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> copyService.calculateCostPerCopy(blueprint));
        assertEquals("Blueprint with Activity 'Invention' found, requires 'Copying'", ex.getMessage());
    }

    @Test
    void copyingBlueprintShouldThrowExceptionIfNoBBOExists(){
        when(itemMock.getAvgCost()).thenReturn(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> copyService.calculateCostPerCopy(blueprint));
        assertEquals("Missing the Blueprint Original for copying.", ex.getMessage());
    }
}