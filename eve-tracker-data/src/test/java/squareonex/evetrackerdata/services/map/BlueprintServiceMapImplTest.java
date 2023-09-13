package squareonex.evetrackerdata.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintKey;
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
        this.dummyItem = new Blueprint(0L, "blueprint", new Activity(), 10, 10);
    }

    @Test
    void findAll() {
        this.unit.map.put(new BlueprintKey(dummyItem.getActivity(), dummyItem.getId()), dummyItem);

        assertTrue(unit.findAll().contains(dummyItem));
    }

    @Test
    void deleteById() {
        this.unit.map.put(new BlueprintKey(dummyItem.getActivity(), dummyItem.getId()), dummyItem);

        unit.deleteById(new BlueprintKey(dummyItem.getActivity(), dummyItem.getId()));
        assertFalse(unit.findAll().contains(dummyItem));
    }

    @Test
    void delete() {
        this.unit.map.put(new BlueprintKey(dummyItem.getActivity(), dummyItem.getId()), dummyItem);

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
        this.unit.map.put(new BlueprintKey(dummyItem.getActivity(), dummyItem.getId()), dummyItem);

        Blueprint byId = unit.findById(new BlueprintKey(dummyItem.getActivity(), dummyItem.getId()));
        assertEquals(dummyItem, byId);
    }
}