package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.BlueprintOriginalCommand;
import squareonex.evetrackerdata.model.BlueprintOriginal;

@Component
public class BlueprintOriginalToBlueprintOriginalCommand implements Converter<BlueprintOriginal, BlueprintOriginalCommand> {
    @Nullable
    @Synchronized
    @Override
    public BlueprintOriginalCommand convert(BlueprintOriginal source) {
        if (source == null)
            return null;
        final BlueprintOriginalCommand target = new BlueprintOriginalCommand();
        target.setId(source.getId());
        target.setBlueprintCost(source.getBlueprintCost());
        target.setMaterialModifier(source.getMaterialModifier());
        target.setTimeModifier(source.getTimeModifier());
        return target;
    }
}
