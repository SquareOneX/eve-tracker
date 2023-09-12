package squareonex.evetrackerdata.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetrackerdata.model.Activity;

import static org.junit.jupiter.api.Assertions.*;

class ActivityServiceMapImplTest {
    private ActivityServiceMapImpl unit;
    private Activity dummyActivity;
    @BeforeEach
    void setUp() {
        this.unit = new ActivityServiceMapImpl();
        this.dummyActivity = new Activity();
        this.dummyActivity.setId(0);
        this.dummyActivity.setName("activity");
    }

    @Test
    void findAll() {
        this.unit.map.put(dummyActivity.getId(), dummyActivity);

        assertTrue(unit.findAll().contains(dummyActivity));
    }

    @Test
    void deleteById() {
        this.unit.map.put(dummyActivity.getId(), dummyActivity);

        unit.deleteById(dummyActivity.getId());
        assertFalse(unit.map.containsValue(dummyActivity));
    }

    @Test
    void delete() {
        this.unit.map.put(dummyActivity.getId(), dummyActivity);

        unit.delete(dummyActivity);
        assertFalse(unit.map.containsValue(dummyActivity));
    }

    @Test
    void saveWithoutAnIdShouldAssignAnId() {
        dummyActivity.setId(null);

        assertTrue(unit.map.isEmpty());
        unit.save(dummyActivity);
        assertNotNull(dummyActivity);
        assertFalse(unit.map.isEmpty());
    }

    @Test
    void saveWithoutAnIdShouldNotOverride(){
        dummyActivity.setId(null);

        assertTrue(unit.map.isEmpty());
        unit.map.put(0, new Activity());

        Activity save = unit.save(dummyActivity);

        assertNotNull(save.getId());
        assertNotEquals(0, save.getId());
        assertEquals(2, unit.map.size());
    }

    @Test
    void saveWithAnIdShouldOverride(){
        Activity activity = new Activity();
        activity.setId(0);
        activity.setName("not a book");
        unit.map.put(0, new Activity());

        unit.save(activity);
        assertEquals("not a book", unit.map.get(activity.getId()).getName());
    }

    @Test
    void findById() {
        this.unit.map.put(dummyActivity.getId(), dummyActivity);

        Activity byId = unit.findById(dummyActivity.getId());
        assertEquals(dummyActivity, byId);
    }
}