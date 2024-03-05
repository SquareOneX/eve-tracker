package squareonex.evetrackerdata.csv;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import squareonex.evetrackerdata.csv.readers.ActivityReader;
import squareonex.evetrackerdata.csv.readers.BlueprintReader;
import squareonex.evetrackerdata.csv.readers.ItemReader;
import squareonex.evetrackerdata.repositories.ActivityRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;
import squareonex.evetrackerdata.repositories.ItemRepository;

import static org.junit.jupiter.api.Assertions.*;

class SdeImportTest {
    @Mock ActivityRepository activityRepositoryMock;
    @Mock ItemRepository itemRepositoryMock;
    @Mock BlueprintRepository blueprintRepositoryMock;
    @Mock BlueprintReader blueprintReaderMock;
    @Mock ActivityReader activityReaderMock;
    @Mock ItemReader itemReaderMock;
    private SdeImport sdeDataLoader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.sdeDataLoader = new SdeImport(
                activityRepositoryMock,
                itemRepositoryMock,
                blueprintRepositoryMock,
                blueprintReaderMock,
                activityReaderMock,
                itemReaderMock
        );
    }

    @Test
    void runShouldExecuteWithoutExceptions() {
        assertDoesNotThrow(() -> sdeDataLoader.run());
    }
}