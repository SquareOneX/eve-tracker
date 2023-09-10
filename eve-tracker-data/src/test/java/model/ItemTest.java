package model;

import org.junit.jupiter.api.Test;
import squareonex.evetrackerdata.model.Item;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    public void equalsShouldOnlyIncludeId(){
        Item item1 = new Item();
        item1.setId(0L);
        item1.setName("name");

        Item item2 = new Item();
        item2.setId(0L);
        item2.setName("name2");

        assertEquals(item1, item2);

        item2.setName("name");
        item2.setId(1L);
        assertNotEquals(item1, item2);
    }

}