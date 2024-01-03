package squareonex.evetracker.commands;

import lombok.Data;
import lombok.NonNull;

@Data
public class BlueprintCopyCommand {
    @NonNull
    private Long id;
    @NonNull
    private final Long blueprintId;
    @NonNull private final String blueprintName;
    private Float materialModifier;
    private Float timeModifier;
    private Integer maxRuns;
    private Float blueprintCost;
}
