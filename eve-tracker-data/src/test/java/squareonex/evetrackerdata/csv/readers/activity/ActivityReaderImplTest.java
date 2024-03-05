package squareonex.evetrackerdata.csv.readers.activity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Activity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ActivityReaderImplTest {
    ActivityReaderImpl reader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.reader = new ActivityReaderImpl();
    }

    @Test
    void readAllShouldNotThrowExceptions() {
        Map<Integer, Activity> activities = assertDoesNotThrow(() -> reader.readAll());
        assertFalse(activities.isEmpty());
    }
}