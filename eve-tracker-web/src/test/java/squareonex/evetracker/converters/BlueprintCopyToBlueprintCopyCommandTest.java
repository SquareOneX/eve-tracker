package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.BlueprintCopyCommand;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintCopy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BlueprintCopyToBlueprintCopyCommandTest extends ConverterTestTemplate {
    private static final Long BPC_ID = 0L;
    private static final Long BLUEPRINT_ID = 1L;
    private static final Integer BLUEPRINT_ACTIVITY_ID = 1;
    private static final Float BPC_COST = 0.0F;
    private static final Float BPC_MATERIAL_MOD = 1.0F;
    private static final Float BPC_TIME_MOD = 1.0F;
    private static final Integer BPC_MAX_RUNS = 10;

    BlueprintCopyToBlueprintCopyCommand converter;

    @BeforeEach
    void setUp() {
        this.converter = new BlueprintCopyToBlueprintCopyCommand();
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        BlueprintCopyCommand converted = converter.convert(new BlueprintCopy());
        assertEquals(new BlueprintCopyCommand(), converted);
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        BlueprintCopy source = createSource();
        BlueprintCopyCommand converted = converter.convert(source);

        assertEquals(BPC_ID, converted.getId());
        assertEquals(BPC_COST, converted.getBlueprintCost());
        assertEquals(BPC_MATERIAL_MOD, converted.getMaterialModifier());
        assertEquals(BPC_TIME_MOD, converted.getTimeModifier());
        assertEquals(BPC_MAX_RUNS, converted.getMaxRuns());
    }

    private BlueprintCopy createSource() {
        BlueprintCopy source = new BlueprintCopy();
        source.setId(BPC_ID);
        source.setBlueprintCost(BPC_COST);
        source.setMaterialModifier(BPC_MATERIAL_MOD);
        source.setTimeModifier(BPC_TIME_MOD);
        source.setMaxRuns(BPC_MAX_RUNS);

        Blueprint blueprint = new Blueprint();
        blueprint.getId().getItemInfo().setId(BLUEPRINT_ID);
        blueprint.getId().getActivity().setId(BLUEPRINT_ACTIVITY_ID);
        source.setBlueprint(blueprint);
        return source;
    }
}