package squareonex.evetracker.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.ids.BlueprintId;
import squareonex.evetrackerdata.repositories.BlueprintRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BlueprintServiceImplTest {
    @InjectMocks BlueprintServiceImpl blueprintService;
    @Mock
    BlueprintRepository blueprintRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBlueprints() {
        blueprintService.getBlueprints();

        verify(blueprintRepositoryMock, times(1)).findAll();
    }
}