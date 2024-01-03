package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import squareonex.evetracker.commands.BlueprintCopyCommand;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.repositories.BlueprintCopyRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BlueprintCopyCommandToBlueprintCopyTest {
    private static final Long BPC_ID = 0L;
    private static final Long BLUEPRINT_ID = 1L;
    private static final String BLUEPRINT_NAME = "Blueprint";
    private static final Float BPC_COST = 50_000F;
    private static final Integer BPC_MAXRUNS = 100;
    private static final Float BPC_MATMOD = .95F;
    private static final Float BPC_TIMEMOD = 1.0F;
    BlueprintCopyCommandToBlueprintCopy converter;
    @Mock
    BlueprintCopyRepository blueprintCopyRepositoryMock;
    @Mock
    BlueprintRepository blueprintRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.converter = new BlueprintCopyCommandToBlueprintCopy(blueprintCopyRepositoryMock, blueprintRepositoryMock);
    }

    @Test
    void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }
    @Test
    void convertingShouldReturnCorrectObject() {
        BlueprintCopyCommand command = new BlueprintCopyCommand(BPC_ID, BLUEPRINT_ID, BLUEPRINT_NAME);
        command.setBlueprintCost(BPC_COST);
        command.setMaxRuns(BPC_MAXRUNS);
        command.setMaterialModifier(BPC_MATMOD);
        command.setTimeModifier(BPC_TIMEMOD);

        when(blueprintRepositoryMock.findById(BLUEPRINT_ID)).thenReturn(Optional.of(new Blueprint(BLUEPRINT_ID, BLUEPRINT_NAME)));

        BlueprintCopy converted = converter.convert(command);

        verify(blueprintRepositoryMock).findById(BLUEPRINT_ID);

        assertEquals(BPC_ID, converted.getId());
        assertEquals(BPC_COST, converted.getBlueprintCost());
        assertEquals(BPC_MATMOD, converted.getMaterialModifier());
        assertEquals(BPC_TIMEMOD, converted.getTimeModifier());
        assertEquals(BPC_MAXRUNS, converted.getMaxRuns());
        assertEquals(BLUEPRINT_ID, converted.getBlueprint().getId());
        assertEquals(BLUEPRINT_NAME, converted.getBlueprint().getName());
    }
}