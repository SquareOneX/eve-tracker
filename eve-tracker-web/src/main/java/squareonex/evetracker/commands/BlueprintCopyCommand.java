package squareonex.evetracker.commands;

import lombok.*;

@Data
public class BlueprintCopyCommand {
    private Long id;
    private BlueprintCommand blueprintCommand;
    private Float materialModifier;
    private Float timeModifier;
    private Integer maxRuns;
    private Float blueprintCost;
}
