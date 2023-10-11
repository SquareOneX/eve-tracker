package squareonex.evetracker.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BlueprintCommand {
    private ActivityCommand activityCommand;
    private ItemCommand itemCommand;
    private Set<BlueprintProductCommand> productCommands;
    private Set<BlueprintMaterialCommand> materialCommands;
    private Set<BlueprintCopyCommand> copyCommands;
    private Set<BlueprintOriginalCommand> originalCommands;
    private Duration duration;
}
