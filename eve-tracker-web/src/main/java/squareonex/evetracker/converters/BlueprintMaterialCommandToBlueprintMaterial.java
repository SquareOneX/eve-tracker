package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.BlueprintMaterialCommand;
import squareonex.evetrackerdata.model.BlueprintMaterial;
import squareonex.evetrackerdata.model.Item;

@Component
public class BlueprintMaterialCommandToBlueprintMaterial implements Converter<BlueprintMaterialCommand, BlueprintMaterial> {
    private final ItemCommandToItem itemCommandToItem;

    public BlueprintMaterialCommandToBlueprintMaterial(ItemCommandToItem itemCommandToItem) {
        this.itemCommandToItem = itemCommandToItem;
    }

    @Nullable
    @Synchronized
    @Override
    public BlueprintMaterial convert(BlueprintMaterialCommand source) {
        if (source == null)
            return null;
        final BlueprintMaterial target = new BlueprintMaterial();
        target.setMaterial(itemCommandToItem.convert(source.getMaterial()));
        target.setQuantity(source.getQuantity());
        return target;
    }
}
