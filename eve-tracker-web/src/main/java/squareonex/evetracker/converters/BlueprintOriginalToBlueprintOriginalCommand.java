package squareonex.evetracker.converters;

import org.springframework.core.convert.converter.Converter;
import squareonex.evetracker.commands.BlueprintOriginalCommand;
import squareonex.evetrackerdata.model.BlueprintOriginal;

public class BlueprintOriginalToBlueprintOriginalCommand implements Converter<BlueprintOriginal, BlueprintOriginalCommand> {
    @Override
    public BlueprintOriginalCommand convert(BlueprintOriginal source) {
        if (source == null)
            return null;
        BlueprintOriginalCommand target = new BlueprintOriginalCommand();
        target.setId(source.getId());
        target.setBlueprintCost(source.getBlueprintCost());
        target.setMaterialModifier(source.getMaterialModifier());
        target.setTimeModifier(source.getTimeModifier());
        return target;
    }
}
