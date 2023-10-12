package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import squareonex.evetracker.commands.BlueprintMaterialCommand;
import squareonex.evetrackerdata.model.BlueprintMaterial;
import squareonex.evetrackerdata.model.Item;

public class BlueprintMaterialCommandToBlueprintMaterial implements Converter<BlueprintMaterialCommand, BlueprintMaterial> {
    @Nullable
    @Synchronized
    @Override
    public BlueprintMaterial convert(BlueprintMaterialCommand source) {
        if (source == null)
            return null;
        BlueprintMaterial target = new BlueprintMaterial();
        if (source.getMaterial() != null){
            Item material = new Item(source.getMaterial().getId(), source.getMaterial().getName());
            material.setPublished(source.getMaterial().getPublished());
            target.setMaterial(material);
        }
        target.setQuantity(source.getQuantity());
        return target;
    }
}
