package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.BlueprintCopyCommand;
import squareonex.evetrackerdata.model.BlueprintCopy;

@Component
public class BlueprintCopyCommandToBlueprintCopy implements Converter<BlueprintCopyCommand, BlueprintCopy> {
    @Nullable
    @Synchronized
    @Override
    public BlueprintCopy convert(BlueprintCopyCommand source) {
        if (source == null)
            return null;
        final BlueprintCopy target = new BlueprintCopy();
        target.setId(source.getId());
        target.setBlueprintCost(source.getBlueprintCost());
        target.setMaxRuns(source.getMaxRuns());
        target.setMaterialModifier(source.getMaterialModifier());
        target.setTimeModifier(source.getTimeModifier());
        return target;
    }
}
