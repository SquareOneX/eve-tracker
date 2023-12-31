package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetrackerdata.model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ItemCommandToItemTest extends ConverterTestTemplate {
    private static final Long ITEM_ID = 0L;
    private static final String ITEM_NAME = "Item";
    private static final Boolean ITEM_PUBBLISHED = true;
    private static final Float ITEM_AVG_COST = 500_000F;
    ItemCommandToItem converter;

    @BeforeEach
    void setUp() {
        converter = new ItemCommandToItem();
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        Item converted = converter.convert(new ItemCommand());
        assertEquals(new Item(), converted);
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        ItemCommand source = createSource();

        Item converted = converter.convert(source);

        assertEquals(ITEM_ID, converted.getId());
        assertEquals(ITEM_NAME, converted.getName());
        assertEquals(ITEM_PUBBLISHED, converted.getPublished());
        assertEquals(ITEM_AVG_COST, converted.getAvgCost());
    }

    private ItemCommand createSource() {
        ItemCommand source = new ItemCommand();
        source.setId(ITEM_ID);
        source.setName(ITEM_NAME);
        source.setPublished(ITEM_PUBBLISHED);
        source.setAvgCost(ITEM_AVG_COST);

        return source;
    }
}