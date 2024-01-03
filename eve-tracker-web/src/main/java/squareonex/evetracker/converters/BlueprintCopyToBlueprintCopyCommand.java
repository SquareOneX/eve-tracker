package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.BlueprintCopyCommand;
import squareonex.evetrackerdata.model.BlueprintCopy;

@Component
public class BlueprintCopyToBlueprintCopyCommand implements Converter<BlueprintCopy, BlueprintCopyCommand> {
    @Nullable
    @Synchronized
    @Override
    public BlueprintCopyCommand convert(BlueprintCopy source) {
        if (source == null)
            return null;

        final BlueprintCopyCommand target = new BlueprintCopyCommand(source.getId(), source.getBlueprint().getId(), source.getBlueprint().getName());
        target.setTimeModifier(source.getTimeModifier());
        target.setMaterialModifier(source.getMaterialModifier());
        target.setBlueprintCost(source.getBlueprintCost());
        target.setMaxRuns(source.getMaxRuns());

        return target;
    }
}
