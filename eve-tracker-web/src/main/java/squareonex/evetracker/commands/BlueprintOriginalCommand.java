package squareonex.evetracker.commands;

import lombok.*;

@Data
public class BlueprintOriginalCommand {
    private Long id;
    private BlueprintCommand blueprintCommand;
    private Float materialModifier;
    private Float timeModifier;
    private Float blueprintCost;
}
