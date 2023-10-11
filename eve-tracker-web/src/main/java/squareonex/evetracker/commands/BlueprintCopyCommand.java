package squareonex.evetracker.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlueprintCopyCommand {
    private Long id;
    private BlueprintCommand blueprintCommand;
    private Float materialModifier;
    private Float timeModifier;
    private Integer maxRuns;
    private Float blueprintCost;
}
