package squareonex.evetracker.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squareonex.evetracker.commands.*;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.BlueprintProduct;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BlueprintCommandToBlueprintTest extends ConverterTestTemplate {
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

    BlueprintCommandToBlueprint converter;

    @BeforeEach
    public void setUp() {
        this.converter = new BlueprintCommandToBlueprint(
                new BlueprintCopyCommandToBlueprintCopy(),
                new BlueprintOriginalCommandToBlueprintOriginal(),
                new BlueprintMaterialCommandToBlueprintMaterial(),
                new BlueprintProductCommandToBlueprintProduct(
                        new ItemCommandToItem()
                ),
                new ActivityCommandToActivity(),
                new ItemCommandToItem()
        );
    }

    @Test
    protected void convertingNullShouldReturnNull() {
        assertNull(converter.convert(null));
    }

    @Test
    protected void convertingEmptyObjectShouldReturnEmptyObject() {
        assertNotNull(converter.convert(new BlueprintCommand()));
    }

    @Test
    protected void convertShouldReturnValidTarget() {
        //arrange
        BlueprintCommand source = setUpSource();

        //act
        Blueprint converted = converter.convert(source);

        //assert
        assertEquals(BLUEPRINT_ID, converted.getItemInfo().getId());
        assertEquals(ACTIVITY_ID, converted.getActivity().getId());
        for (BlueprintProduct product : converted.getProducts()) {
            Set.of(PRODUCT_ID_1, PRODUCT_ID_2).contains(product.getProduct().getId());
        }
    }
    private BlueprintCommand setUpSource() {
        BlueprintCommand source = new BlueprintCommand();
        ItemCommand itemCommand = new ItemCommand();
        itemCommand.setId(BLUEPRINT_ID);
        source.setItemCommand(itemCommand);

        ActivityCommand activityCommand = new ActivityCommand();
        activityCommand.setId(ACTIVITY_ID);
        source.setActivityCommand(activityCommand);

        BlueprintProductCommand productCommand1 = new BlueprintProductCommand();
        BlueprintProductCommand productCommand2 = new BlueprintProductCommand();
        ItemCommand product1 = new ItemCommand();
        ItemCommand product2 = new ItemCommand();
        product1.setId(PRODUCT_ID_1);
        product2.setId(PRODUCT_ID_2);
        productCommand1.setProduct(product1);
        productCommand2.setProduct(product2);
        source.setProductCommands(Set.of(productCommand1, productCommand2));

        BlueprintMaterialCommand materialCommand1 = new BlueprintMaterialCommand();
        BlueprintMaterialCommand materialCommand2 = new BlueprintMaterialCommand();
        ItemCommand material1 = new ItemCommand();
        ItemCommand material2 = new ItemCommand();
        material1.setId(MATERIAL_ID_1);
        material2.setId(MATERIAL_ID_2);
        materialCommand1.setMaterial(material1);
        materialCommand2.setMaterial(material2);
        source.setMaterialCommands(Set.of(materialCommand1, materialCommand2));

        BlueprintOriginalCommand originalCommand1 = new BlueprintOriginalCommand();
        BlueprintOriginalCommand originalCommand2 = new BlueprintOriginalCommand();
        originalCommand1.setId(BPO_ID_1);
        originalCommand2.setId(BPO_ID_2);
        source.setOriginalCommands(Set.of(originalCommand1, originalCommand2));

        BlueprintCopyCommand copyCommand1 = new BlueprintCopyCommand();
        BlueprintCopyCommand copyCommand2 = new BlueprintCopyCommand();
        copyCommand1.setId(BPC_ID_1);
        copyCommand2.setId(BPC_ID_2);
        source.setCopyCommands(Set.of(copyCommand1, copyCommand2));

        return source;
    }
}