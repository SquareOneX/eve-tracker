package squareonex.evetrackerdata.csv.readers.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetrackerdata.model.Item;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemReaderImplTest {
    ItemReaderImpl reader;

    @BeforeEach
    void setUp() {
        this.reader = new ItemReaderImpl();
    }

    @Test
    void readdAllShouldNotThrowExceptions() {
        List<Item> items = assertDoesNotThrow(() -> reader.readAll());
        assertFalse(items.isEmpty());
    }
}