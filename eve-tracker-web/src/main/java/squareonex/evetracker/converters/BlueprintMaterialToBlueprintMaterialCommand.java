package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import squareonex.evetracker.commands.BlueprintMaterialCommand;
import squareonex.evetracker.commands.ItemCommand;
import squareonex.evetrackerdata.model.BlueprintMaterial;

public class BlueprintMaterialToBlueprintMaterialCommand implements Converter<BlueprintMaterial, BlueprintMaterialCommand> {
    @Nullable
    @Synchronized
    @Override
    public BlueprintMaterialCommand convert(BlueprintMaterial source) {
        if (source == null)
            return null;
        BlueprintMaterialCommand target = new BlueprintMaterialCommand();
        if (source.getMaterial() != null){
            ItemCommand material = new ItemCommand();
            material.setId(source.getMaterial().getId());
            material.setName(source.getMaterial().getName());
            material.setPublished(source.getMaterial().getPublished());
            target.setMaterial(material);
        }
        target.setQuantity(source.getQuantity());
        return target;
    }
}
