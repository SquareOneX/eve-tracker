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
        final BlueprintCopyCommand target = new BlueprintCopyCommand();
        target.setId(source.getId());
        target.setMaxRuns(source.getMaxRuns());
        target.setTimeModifier(source.getTimeModifier());
        target.setMaterialModifier(source.getMaterialModifier());
        target.setBlueprintCost(source.getBlueprintCost());
        return target;
    }
}
