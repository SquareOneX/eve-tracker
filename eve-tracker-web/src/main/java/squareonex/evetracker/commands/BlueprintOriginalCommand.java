package squareonex.evetracker.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlueprintOriginalCommand {
    private Long id;
    private BlueprintCommand blueprintCommand;
    private Float materialModifier;
    private Float timeModifier;
    private Float blueprintCost;
}
