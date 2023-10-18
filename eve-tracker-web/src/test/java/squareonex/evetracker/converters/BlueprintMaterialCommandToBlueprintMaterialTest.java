package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.BlueprintMaterialCommand;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetrackerdata.model.BlueprintMaterial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BlueprintMaterialCommandToBlueprintMaterialTest extends ConverterTestTemplate {
    private static final Long ITEM_ID = 0L;
    private static final Integer QTY = 100;
    BlueprintMaterialCommandToBlueprintMaterial converter;
    @BeforeEach
    void setUp() {
        this.converter = new BlueprintMaterialCommandToBlueprintMaterial(new ItemCommandToItem());
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        assertEquals(new BlueprintMaterial(), converter.convert(new BlueprintMaterialCommand()));
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        BlueprintMaterialCommand source = createSource();

        BlueprintMaterial converted = converter.convert(source);
        assertEquals(ITEM_ID, converted.getMaterial().getId());
        assertEquals(QTY, converted.getQuantity());
    }

    private BlueprintMaterialCommand createSource() {
        BlueprintMaterialCommand source;
        source = new BlueprintMaterialCommand();
        ItemCommand material = new ItemCommand();
        material.setId(ITEM_ID);
        source.setMaterial(material);
        source.setQuantity(QTY);
        return source;
    }
}