package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.*;
import squareonex.evetrackerdata.model.*;
import squareonex.evetrackerdata.model.ids.BlueprintId;

import java.time.Duration;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BlueprintToBlueprintCommandTest extends ConverterTestTemplate {
    private static final Long BLUEPRINT_ID = 0L;
    private static final Integer ACTIVITY_ID = 0;
    private static final Long PRODUCT_ID_1 = 1L;
    private static final Long PRODUCT_ID_2 = 2L;
    private static final Long MATERIAL_ID_1 = 3L;
    private static final Long MATERIAL_ID_2 = 4L;
    private static final Long BPO_ID_1 = 0L;
    private static final Long BPO_ID_2 = 1L;
    private static final Long BPC_ID_1 = 0L;
    private static final Long BPC_ID_2 = 1L;
    private static final int BPC_QTY = 1;
    private static final float BPC_PROB = 1F;
    private static final Duration BP_DURATION = Duration.ofHours(2);
    BlueprintToBlueprintCommand converter;

    @BeforeEach
    void setUp() {
        this.converter = new BlueprintToBlueprintCommand(
                new ActivityToActivityCommand(),
                new ItemToItemCommand(),
                new BlueprintCopyToBlueprintCopyCommand(),
                new BlueprintProductToBlueprintProductCommand(new ItemToItemCommand()),
                new BlueprintMaterialToBlueprintMaterialCommand(),
                new BlueprintOriginalToBlueprintOriginalCommand()
        );
    }

    @Test
    @Override
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    @Override
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        assertEquals(new BlueprintCommand(), converter.convert(new Blueprint()));
    }

    @Test
    @Override
    protected void convertShouldReturnValidTarget() {
        Blueprint source = createSource();

        BlueprintCommand converted = converter.convert(source);
        assertEquals(BLUEPRINT_ID, converted.getItemCommand().getId());
        assertEquals(ACTIVITY_ID, converted.getActivityCommand().getId());
        assertEquals(2, converted.getProductCommands().size());
        for (BlueprintProductCommand command : converted.getProductCommands()) {
            assertTrue(Set.of(PRODUCT_ID_1, PRODUCT_ID_2).contains(command.getProduct().getId()));
        }
        for (BlueprintMaterialCommand command : converted.getMaterialCommands()) {
            assertTrue(Set.of(MATERIAL_ID_1, MATERIAL_ID_2).contains(command.getMaterial().getId()));
        }
        for (BlueprintOriginalCommand command : converted.getOriginalCommands()) {
            assertTrue(Set.of(BPO_ID_1, BPO_ID_2).contains(command.getId()));
        }
        for (BlueprintCopyCommand command : converted.getCopyCommands()) {
            assertTrue(Set.of(BPC_ID_1, BPC_ID_2).contains(command.getId()));
        }
        assertEquals(BP_DURATION, converted.getDuration());

    }

    private Blueprint createSource() {
        Blueprint source = new Blueprint();
        Item item = new Item();
        item.setId(BLUEPRINT_ID);

        Activity activity = new Activity();
        activity.setId(ACTIVITY_ID);
        BlueprintId blueprintId = new BlueprintId(item, activity);
        source.setId(blueprintId);

        Item product1 = new Item(PRODUCT_ID_1, null);
        Item product2 = new Item(PRODUCT_ID_2, null);

        BlueprintProduct blueprintProduct1 = new BlueprintProduct(source, product1, BPC_QTY, BPC_PROB);
        BlueprintProduct blueprintProduct2 = new BlueprintProduct(source, product2, BPC_QTY, BPC_PROB);
        source.getProducts().add(blueprintProduct1);
        source.getProducts().add(blueprintProduct2);

        product1.setBlueprints(Set.of(blueprintProduct1));
        product2.setBlueprints(Set.of(blueprintProduct2));

        Item material1 = new Item(MATERIAL_ID_1, null);
        Item material2 = new Item(MATERIAL_ID_2, null);
        BlueprintMaterial blueprintMaterial1 = new BlueprintMaterial(source, material1, BPC_QTY);
        BlueprintMaterial blueprintMaterial2 = new BlueprintMaterial(source, material2, BPC_QTY);
        source.getMaterials().add(blueprintMaterial1);
        source.getMaterials().add(blueprintMaterial2);

        BlueprintOriginal blueprintOriginal1 = new BlueprintOriginal();
        BlueprintOriginal blueprintOriginal2 = new BlueprintOriginal();
        blueprintOriginal1.setId(BPO_ID_1);
        blueprintOriginal2.setId(BPO_ID_2);
        source.setOriginals(Set.of(blueprintOriginal1, blueprintOriginal2));

        BlueprintCopy blueprintCopy1 = new BlueprintCopy();
        BlueprintCopy blueprintCopy2 = new BlueprintCopy();
        blueprintCopy1.setId(BPC_ID_1);
        blueprintCopy2.setId(BPC_ID_2);
        source.setCopies(Set.of(blueprintCopy1, blueprintCopy2));

        source.setDuration(BP_DURATION);
        return source;
    }
}