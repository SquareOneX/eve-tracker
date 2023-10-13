package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.BlueprintOriginalCommand;
import squareonex.evetrackerdata.model.BlueprintOriginal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BlueprintOriginalCommandToBlueprintOriginalTest extends ConverterTestTemplate {
    private static final Long BPO_ID = 0L;
    private static final Float BPO_COST = 1000F;
    private static final Float BPO_MATERIAL_MOD = .9F;
    private static final Float BPO_TIME_MOD = .95F;
    BlueprintOriginalCommandToBlueprintOriginal converter;
    @BeforeEach
    void setUp() {
        this.converter = new BlueprintOriginalCommandToBlueprintOriginal();
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        assertEquals(new BlueprintOriginal(), converter.convert(new BlueprintOriginalCommand()));
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        BlueprintOriginalCommand source = new BlueprintOriginalCommand();
        source.setId(BPO_ID);
        source.setBlueprintCost(BPO_COST);
        source.setTimeModifier(BPO_TIME_MOD);
        source.setMaterialModifier(BPO_MATERIAL_MOD);

        BlueprintOriginal converted = converter.convert(source);
        assertEquals(BPO_ID, converted.getId());
        assertEquals(BPO_COST, converted.getBlueprintCost());
        assertEquals(BPO_TIME_MOD, converted.getTimeModifier());
        assertEquals(BPO_MATERIAL_MOD, converted.getMaterialModifier());
    }
}