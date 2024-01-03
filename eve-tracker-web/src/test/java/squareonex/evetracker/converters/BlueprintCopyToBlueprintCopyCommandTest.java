package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import squareonex.evetracker.commands.BlueprintCopyCommand;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintCopy;

import static org.junit.jupiter.api.Assertions.*;

class BlueprintCopyToBlueprintCopyCommandTest {
    private static final Long BPC_ID = 0L;
    private static final Long BLUEPRINT_ID = 1L;
    private static final String BLUEPRINT_NAME = "Blueprint";
    private static final Float BPC_COST = 50_000F;
    private static final Integer BPC_MAXRUNS = 100;
    private static final Float BPC_MATMOD = .95F;
    private static final Float BPC_TIMEMOD = 1.0F;
    private BlueprintCopyToBlueprintCopyCommand converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.converter = new BlueprintCopyToBlueprintCopyCommand();
    }

    @Test
    void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void convertingObjectShouldReturnMatchingObject() {
        BlueprintCopy source = new BlueprintCopy(new Blueprint(BLUEPRINT_ID, BLUEPRINT_NAME), BPC_MAXRUNS, BPC_COST);
        source.setId(BPC_ID);
        source.setTimeModifier(BPC_TIMEMOD);
        source.setMaterialModifier(BPC_MATMOD);

        BlueprintCopyCommand converted = converter.convert(source);
        assertEquals(BPC_ID, converted.getId());
        assertEquals(BPC_COST, converted.getBlueprintCost());
        assertEquals(BPC_MATMOD, converted.getMaterialModifier());
        assertEquals(BPC_TIMEMOD, converted.getTimeModifier());
        assertEquals(BPC_MAXRUNS, converted.getMaxRuns());
        assertEquals(BLUEPRINT_ID, converted.getBlueprintId());
        assertEquals(BLUEPRINT_NAME, converted.getBlueprintName());
    }
}