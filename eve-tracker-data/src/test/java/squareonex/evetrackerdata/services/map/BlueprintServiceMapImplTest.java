package squareonex.evetrackerdata.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.services.ActivityService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BlueprintServiceMapImplTest {
    private BlueprintServiceMapImpl unit;
    private Blueprint dummyItem;
    @Mock
    ActivityService activityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.unit = new BlueprintServiceMapImpl(activityService);
        this.dummyItem = new Blueprint();
        dummyItem.setId(0L);
        dummyItem.setName("blueprint");
        dummyItem.setActivity(new Activity());
        dummyItem.setMaxRuns(10);
        dummyItem.setQuantity(10);
    }

    @Test
    void findAll() {
        this.unit.map.put(dummyItem.getKey(), dummyItem);

        assertTrue(unit.findAll().contains(dummyItem));
    }

    @Test
    void deleteById() {
        this.unit.map.put(dummyItem.getKey(), dummyItem);

        unit.deleteById(dummyItem.getKey());
        assertFalse(unit.findAll().contains(dummyItem));
    }

    @Test
    void delete() {
        this.unit.map.put(dummyItem.getKey(), dummyItem);

        unit.delete(dummyItem);
        assertFalse(unit.findAll().contains(dummyItem));

    }

    @Test
    void saveWithUndefinedIdShouldFailSilently() {
        this.dummyItem.setId(null);

        assertNull(unit.save(dummyItem));
        assertTrue(unit.map.isEmpty());
    }
    @Test
    void saveWithUndefinedActivityShouldFailSilently() {
        this.dummyItem.setActivity(null);

        assertNull(unit.save(dummyItem));
        assertTrue(unit.map.isEmpty());
    }
    @Test
    void saveWithNewActivityShouldSaveActivity() {
        this.dummyItem.setActivity(new Activity());
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        when(activityService.findById(any(Integer.class))).thenReturn(null);

        unit.save(dummyItem);

        verify(activityService).findById(argumentCaptor.capture());
        assertEquals(dummyItem.getActivity().getId(), argumentCaptor.getValue());
    }


    @Test
    void findById() {
        this.unit.map.put(dummyItem.getKey(), dummyItem);

        Blueprint byId = unit.findById(dummyItem.getKey());
        assertEquals(dummyItem, byId);
    }
}