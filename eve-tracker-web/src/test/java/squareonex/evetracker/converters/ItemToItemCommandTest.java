package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintProduct;
import squareonex.evetrackerdata.model.Item;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ItemToItemCommandTest extends ConverterTestTemplate {
    private static final Long ITEM_ID = 0L;
    private static final Boolean ITEM_PUBLISHED = true;
    private static final String ITEM_NAME = "item";
    private static final Long BLUEPRINT_ID = 1L;
    ItemToItemCommand converter;
    @BeforeEach
    void setUp() {
        this.converter = new ItemToItemCommand();
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        ItemCommand converted = converter.convert(new Item());
        assertEquals(new ItemCommand(), converted);
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        Item source = createSource();
        ItemCommand converted = converter.convert(source);

        assertEquals(ITEM_ID, converted.getId());
        assertEquals(ITEM_PUBLISHED, converted.getPublished());
        assertEquals(ITEM_NAME, converted.getName());
    }

    private Item createSource() {
        Item source = new Item();
        source.setId(ITEM_ID);
        source.setPublished(ITEM_PUBLISHED);
        source.setName(ITEM_NAME);
        Set<BlueprintProduct> blueprints = new HashSet<>();
        Blueprint blueprint = new Blueprint();
        blueprint.setItemInfo(new Item(BLUEPRINT_ID, null));
        blueprints.add(new BlueprintProduct(blueprint, source, 1));
        source.setBlueprints(blueprints);
        return source;
    }
}