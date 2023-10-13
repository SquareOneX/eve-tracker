package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.BlueprintProductCommand;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetrackerdata.model.BlueprintProduct;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BlueprintProductCommandToBlueprintProductTest extends ConverterTestTemplate {
    private static final Long PRODUCT_ID = 0L;
    private static final Integer QTY = 100;
    private static final Float PROPABILITY = .9F;
    BlueprintProductCommandToBlueprintProduct converter;
    @BeforeEach
    void setUp() {
        this.converter = new BlueprintProductCommandToBlueprintProduct(new ItemCommandToItem());
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        assertEquals(new BlueprintProduct(), converter.convert(new BlueprintProductCommand()));
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        BlueprintProductCommand source = new BlueprintProductCommand();
        ItemCommand itemCommand = new ItemCommand();
        itemCommand.setId(PRODUCT_ID);
        source.setProduct(itemCommand);
        source.setQuantity(QTY);
        source.setProbability(PROPABILITY);

        BlueprintProduct converted = converter.convert(source);
        assertEquals(PRODUCT_ID, converted.getProduct().getId());
        assertEquals(QTY, converted.getQuantity());
        assertEquals(PROPABILITY, converted.getProbability());
    }
}