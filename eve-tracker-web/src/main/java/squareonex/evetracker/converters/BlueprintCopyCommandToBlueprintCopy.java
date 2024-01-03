package squareonex.evetracker.converters;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetracker.commands.BlueprintCopyCommand;
import squareonex.evetrackerdata.model.BlueprintCopy;
import squareonex.evetrackerdata.repositories.BlueprintCopyRepository;
import squareonex.evetrackerdata.repositories.BlueprintRepository;

@Component
public class BlueprintCopyCommandToBlueprintCopy implements Converter<BlueprintCopyCommand, BlueprintCopy> {
    private final BlueprintRepository blueprintRepository;

    public BlueprintCopyCommandToBlueprintCopy(BlueprintCopyRepository blueprintCopyRepository, BlueprintRepository blueprintRepository) {
        this.blueprintRepository = blueprintRepository;
    }

    @Nullable
    @Synchronized
    @Override
    public BlueprintCopy convert(BlueprintCopyCommand source) {
        if (source == null)
            return null;

        final BlueprintCopy target = new BlueprintCopy();
        target.setId(source.getId());
        target.setMaterialModifier(source.getMaterialModifier());
        target.setTimeModifier(source.getTimeModifier());
        target.setMaxRuns(source.getMaxRuns());
        target.setBlueprintCost(source.getBlueprintCost());

        blueprintRepository.findById(source.getBlueprintId()).ifPresentOrElse(target::setBlueprint, () -> {
            throw new IllegalArgumentException("No blueprint with id=" + source.getBlueprintId() + " found in database.");
        });

        return target;
    }
}
