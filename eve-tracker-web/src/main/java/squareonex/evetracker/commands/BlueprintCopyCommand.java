package squareonex.evetracker.commands;

import lombok.Data;

@Data
public class BlueprintCopyCommand {
    private Long id;
    private Float materialModifier = 1F;
    private Float timeModifier = 1F;
    private Integer maxRuns;
    private Float blueprintCost;
}
