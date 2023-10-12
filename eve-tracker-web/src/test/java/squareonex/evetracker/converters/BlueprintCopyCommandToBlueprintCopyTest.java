package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.BlueprintCopyCommand;
import squareonex.evetrackerdata.model.BlueprintCopy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BlueprintCopyCommandToBlueprintCopyTest extends ConverterTestTemplate{
    private static final Long BPC_ID = 0L;
    private static final Float BPC_COST = 0.0F;
    private static final Float BPC_MATERIAL_MOD = 1.0F;
    private static final Float BPC_TIME_MOD = 1.0F;
    private static final Integer BPC_MAX_RUNS = 10;
    BlueprintCopyCommandToBlueprintCopy converter;
    @BeforeEach
    protected void setUp() {
        this.converter = new BlueprintCopyCommandToBlueprintCopy();
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        assertEquals(new BlueprintCopy(), converter.convert(new BlueprintCopyCommand()));
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        BlueprintCopyCommand source = createSource();

        BlueprintCopy converted = converter.convert(source);

        assertEquals(BPC_ID, converted.getId());
        assertEquals(BPC_COST, converted.getBlueprintCost());
        assertEquals(BPC_MATERIAL_MOD, converted.getMaterialModifier());
        assertEquals(BPC_TIME_MOD, converted.getTimeModifier());
        assertEquals(BPC_MAX_RUNS, converted.getMaxRuns());
    }

    private BlueprintCopyCommand createSource() {
        BlueprintCopyCommand source = new BlueprintCopyCommand();
        source.setId(BPC_ID);
        source.setMaterialModifier(BPC_MATERIAL_MOD);
        source.setTimeModifier(BPC_TIME_MOD);
        source.setMaxRuns(BPC_MAX_RUNS);
        source.setBlueprintCost(BPC_COST);
        return source;
    }
}