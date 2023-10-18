package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.BlueprintMaterialCommand;
import squareonex.evetrackerdata.model.BlueprintMaterial;
import squareonex.evetrackerdata.model.Item;

import static org.junit.jupiter.api.Assertions.*;

class BlueprintMaterialToBlueprintMaterialCommandTest extends ConverterTestTemplate {
    private static final Long ITEM_ID = 0L;
    private static final Integer QTY = 100;
    BlueprintMaterialToBlueprintMaterialCommand converter;
    @BeforeEach
    void setUp() {
        this.converter = new BlueprintMaterialToBlueprintMaterialCommand(new ItemToItemCommand());
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        assertEquals(new BlueprintMaterialCommand(), converter.convert(new BlueprintMaterial()));
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        BlueprintMaterial source = createSource();

        BlueprintMaterialCommand converted = converter.convert(source);
        assertNotNull(converted.getMaterial());
        assertEquals(ITEM_ID, converted.getMaterial().getId());
        assertEquals(QTY, converted.getQuantity());
    }

    private BlueprintMaterial createSource() {
        BlueprintMaterial source = new BlueprintMaterial();
        source.setMaterial(new Item(ITEM_ID, null));
        source.setQuantity(QTY);
        return source;
    }
}