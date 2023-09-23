package squareonex.evetrackerdata.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetrackerdata.model.Item;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceMapImplTest {
    private ProductServiceMapImpl unit;
    private Item dummyItem;

    @BeforeEach
    void setUp() {
        this.unit = new ProductServiceMapImpl();
        dummyItem = new Item();
        dummyItem.setId(0L);
        dummyItem.setName("name");
    }

    @Test
    void findAll() {
        Long id = dummyItem.getId();
        unit.map.put(dummyItem.getId(), dummyItem);

        assertTrue(unit.map.containsValue(dummyItem));
        assertTrue(unit.map.containsKey(id));
        assertEquals(id, dummyItem.getId());
    }

    @Test
    void deleteById() {
        unit.map.put(dummyItem.getId(), dummyItem);

        unit.deleteById(dummyItem.getId());
        assertTrue(unit.map.isEmpty());
    }

    @Test
    void delete() {
        unit.map.put(dummyItem.getId(), dummyItem);

        unit.delete(dummyItem);
        assertTrue(unit.map.isEmpty());
    }

    @Test
    void saveWithoutAnIdShouldAssignAnId() {
        dummyItem.setId(null);

        assertTrue(unit.map.isEmpty());
        unit.save(dummyItem);
        assertNotNull(dummyItem);
        assertFalse(unit.map.isEmpty());
    }

    @Test
    void saveWithoutAnIdShouldNotOverride(){
        dummyItem.setId(null);

        assertTrue(unit.map.isEmpty());
        unit.map.put(0L, new Item());

        Item save = unit.save(dummyItem);

        assertNotNull(save.getId());
        assertNotEquals(0L, save.getId());
        assertEquals(2, unit.map.size());
    }

    @Test
    void saveWithAnIdShouldOverride(){
        Item item = new Item();
        item.setName("not a book");
        unit.map.put(0L, item);

        unit.save(dummyItem);
        assertNotEquals("not a book", unit.map.get(dummyItem.getId()).getName());
    }

    @Test
    void findById() {
        unit.map.put(dummyItem.getId(), dummyItem);

        Item item = unit.findById(dummyItem.getId());
        assertEquals(dummyItem, item);
    }
}