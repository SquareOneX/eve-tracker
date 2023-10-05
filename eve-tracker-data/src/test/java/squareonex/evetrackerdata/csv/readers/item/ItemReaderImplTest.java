package squareonex.evetrackerdata.csv.readers.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ItemReaderImplTest {
    private ItemReaderImpl unit;

    @BeforeEach
    void setUp() {
        this.unit = new ItemReaderImpl();
    }

    @Test
    void readAll() throws FileNotFoundException {
        unit.readAll();
    }
}