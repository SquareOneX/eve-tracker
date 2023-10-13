package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.BlueprintProductCommand;
import squareonex.evetrackerdata.model.BlueprintProduct;
import squareonex.evetrackerdata.model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BlueprintProductToBlueprintProductCommandTest extends ConverterTestTemplate {
    private static final Long PRODUCT_ID = 0L;
    private static final Integer QTY = 100;
    private static final Float PROPABILITY = .9F;
    BlueprintProductToBlueprintProductCommand converter;
    @BeforeEach
    void setUp() {
        this.converter = new BlueprintProductToBlueprintProductCommand(new ItemToItemCommand());
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        assertEquals(new BlueprintProductCommand(), converter.convert(new BlueprintProduct()));
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        BlueprintProduct source = new BlueprintProduct();
        Item item = new Item(PRODUCT_ID, null);
        source.setProduct(item);
        source.setQuantity(QTY);
        source.setProbability(PROPABILITY);

        BlueprintProductCommand converted = converter.convert(source);
        assertEquals(PRODUCT_ID, converted.getProduct().getId());
        assertEquals(QTY, converted.getQuantity());
        assertEquals(PROPABILITY, converted.getProbability());
    }
}