package squareonex.evetrackerdata.csv.readers.blueprint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BlueprintReaderImplTest {
    @Mock
    ActivityRepository activityRepository;
    @Mock
    ItemRepository itemRepository;
    BlueprintReaderImpl reader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.reader = new BlueprintReaderImpl();
    }

    @Test
    void readAllShouldNotThrowExceptions() {
        Map<Long, Blueprint> blueprints = assertDoesNotThrow(() -> reader.readAll(new HashMap<>(), new HashMap<>()));
        assertFalse(blueprints.isEmpty());
    }
}