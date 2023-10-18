package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.*;
import squareonex.evetrackerdata.model.*;
import squareonex.evetrackerdata.model.ids.BlueprintId;

import java.util.HashSet;
import java.util.Set;

@Component
public class BlueprintCommandToBlueprint implements Converter<BlueprintCommand, Blueprint> {
    private final Converter<BlueprintCopyCommand, BlueprintCopy> copyCommandToCopy;
    private final Converter<BlueprintOriginalCommand, BlueprintOriginal> originalCommandToOriginal;
    private final Converter<BlueprintMaterialCommand, BlueprintMaterial> materialCommandToMaterial;
    private final Converter<BlueprintProductCommand, BlueprintProduct> productCommandToProduct;
    private final Converter<ActivityCommand, Activity> activityCommandToActivity;
    private final Converter<ItemCommand, Item> itemCommandToItem;

    public BlueprintCommandToBlueprint(
            Converter<BlueprintCopyCommand, BlueprintCopy> copyCommandToCopy,
            Converter<BlueprintOriginalCommand, BlueprintOriginal> originalCommandToOriginal,
            Converter<BlueprintMaterialCommand, BlueprintMaterial> materialCommandToMaterial,
            Converter<BlueprintProductCommand, BlueprintProduct> productCommandToProduct,
            Converter<ActivityCommand, Activity> activityCommandToActivity,
            Converter<ItemCommand, Item> itemCommandToItem
    ) {
        this.copyCommandToCopy = copyCommandToCopy;
        this.originalCommandToOriginal = originalCommandToOriginal;
        this.materialCommandToMaterial = materialCommandToMaterial;
        this.productCommandToProduct = productCommandToProduct;
        this.activityCommandToActivity = activityCommandToActivity;
        this.itemCommandToItem = itemCommandToItem;
    }

    @Synchronized
    @Nullable
    @Override
    public Blueprint convert(BlueprintCommand source) {
        if (source == null)
            return null;
        final Blueprint target = new Blueprint();
        target.setId(new BlueprintId(new Item(), new Activity()));

        source.getCopyCommands().forEach((command) -> target.getCopies().add(copyCommandToCopy.convert(command)));

        source.getOriginalCommands().forEach((command) -> target.getOriginals().add(originalCommandToOriginal.convert(command)));

        source.getMaterialCommands().forEach((command) -> target.getMaterials().add(materialCommandToMaterial.convert(command)));

        source.getProductCommands().forEach((command) -> target.getProducts().add(productCommandToProduct.convert(command)));

        target.setActivity(activityCommandToActivity.convert(source.getActivityCommand()));
        target.setItemInfo(itemCommandToItem.convert(source.getItemCommand()));

        target.setDuration(source.getDuration());
        return target;
    }
}
