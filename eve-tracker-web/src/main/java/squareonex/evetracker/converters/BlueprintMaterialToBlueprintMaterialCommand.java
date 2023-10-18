package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.BlueprintMaterialCommand;
import squareonex.evetrackerdata.model.BlueprintMaterial;

@Component
public class BlueprintMaterialToBlueprintMaterialCommand implements Converter<BlueprintMaterial, BlueprintMaterialCommand> {
    private final ItemToItemCommand itemToItemCommand;

    public BlueprintMaterialToBlueprintMaterialCommand(ItemToItemCommand itemToItemCommand) {
        this.itemToItemCommand = itemToItemCommand;
    }

    @Nullable
    @Synchronized
    @Override
    public BlueprintMaterialCommand convert(BlueprintMaterial source) {
        if (source == null)
            return null;
        final BlueprintMaterialCommand target = new BlueprintMaterialCommand();
        target.setMaterial(itemToItemCommand.convert(source.getMaterial()));
        target.setQuantity(source.getQuantity());
        return target;
    }
}
