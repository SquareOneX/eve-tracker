package squareonex.evetracker.converters;

import org.springframework.core.convert.converter.Converter;
import squareonex.evetracker.commands.BlueprintOriginalCommand;
import squareonex.evetrackerdata.model.BlueprintOriginal;

public class BlueprintOriginalCommandToBlueprintOriginal implements Converter<BlueprintOriginalCommand, BlueprintOriginal> {
    @Override
    public BlueprintOriginal convert(BlueprintOriginalCommand source) {
        if (source == null)
            return null;
        BlueprintOriginal target = new BlueprintOriginal();
        target.setId(source.getId());
        target.setBlueprintCost(source.getBlueprintCost());
        target.setMaterialModifier(source.getMaterialModifier());
        target.setTimeModifier(source.getTimeModifier());
        return target;
    }
}
