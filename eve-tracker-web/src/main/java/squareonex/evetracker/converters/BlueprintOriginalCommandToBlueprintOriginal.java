package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.BlueprintOriginalCommand;
import squareonex.evetrackerdata.model.BlueprintOriginal;

@Component
public class BlueprintOriginalCommandToBlueprintOriginal implements Converter<BlueprintOriginalCommand, BlueprintOriginal> {
    @Nullable
    @Synchronized
    @Override
    public BlueprintOriginal convert(BlueprintOriginalCommand source) {
        if (source == null)
            return null;
        final BlueprintOriginal target = new BlueprintOriginal();
        target.setId(source.getId());
        target.setBlueprintCost(source.getBlueprintCost());
        target.setMaterialModifier(source.getMaterialModifier());
        target.setTimeModifier(source.getTimeModifier());
        return target;
    }
}
