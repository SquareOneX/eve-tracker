package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.*;
import squareonex.evetrackerdata.model.*;

@Component
public class BlueprintToBlueprintCommand implements Converter<Blueprint, BlueprintCommand> {
    private final Converter<Activity, ActivityCommand> activityToActivityCommand;
    private final Converter<Item, ItemCommand> itemToItemCommand;
    private final Converter<BlueprintCopy, BlueprintCopyCommand> copyToCopyCommand;
    private final Converter<BlueprintProduct, BlueprintProductCommand> productToProductCommand;
    private final Converter<BlueprintMaterial, BlueprintMaterialCommand> materialToMaterialCommand;
    private final Converter<BlueprintOriginal, BlueprintOriginalCommand> originalToOriginalCommand;

    public BlueprintToBlueprintCommand(Converter<Activity, ActivityCommand> activityToActivityCommand, Converter<Item, ItemCommand> itemToItemCommand, Converter<BlueprintCopy, BlueprintCopyCommand> copyToCopyCommand, Converter<BlueprintProduct, BlueprintProductCommand> productToProductCommand, Converter<BlueprintMaterial, BlueprintMaterialCommand> materialToMaterialCommand, Converter<BlueprintOriginal, BlueprintOriginalCommand> originalToOriginalCommand) {
        this.activityToActivityCommand = activityToActivityCommand;
        this.itemToItemCommand = itemToItemCommand;
        this.copyToCopyCommand = copyToCopyCommand;
        this.productToProductCommand = productToProductCommand;
        this.materialToMaterialCommand = materialToMaterialCommand;
        this.originalToOriginalCommand = originalToOriginalCommand;
    }

    @Nullable
    @Synchronized
    @Override
    public BlueprintCommand convert(Blueprint source) {
        if (source == null)
            return null;
        final BlueprintCommand target = new BlueprintCommand();
        target.setActivityCommand(activityToActivityCommand.convert(source.getActivity()));
        target.setItemCommand(itemToItemCommand.convert(source.getItemInfo()));

        source.getCopies().forEach((a) -> target.getCopyCommands().add(copyToCopyCommand.convert(a)));

        source.getProducts().forEach(a -> target.getProductCommands().add(productToProductCommand.convert(a)));

        source.getMaterials().forEach(a -> target.getMaterialCommands().add(materialToMaterialCommand.convert(a)));

        source.getOriginals().forEach(a -> target.getOriginalCommands().add(originalToOriginalCommand.convert(a)));

        target.setDuration(source.getDuration());
        return target;
    }
}
