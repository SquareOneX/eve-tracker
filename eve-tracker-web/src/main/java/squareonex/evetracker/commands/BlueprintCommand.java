package squareonex.evetracker.commands;

import lombok.Data;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Data
public class BlueprintCommand {
    private ActivityCommand activityCommand = new ActivityCommand();
    private ItemCommand itemCommand = new ItemCommand();
    private Duration duration;
    private Set<BlueprintProductCommand> productCommands = new HashSet<>();
    private Set<BlueprintMaterialCommand> materialCommands = new HashSet<>();
    private Set<BlueprintCopyCommand> copyCommands = new HashSet<>();
    private Set<BlueprintOriginalCommand> originalCommands = new HashSet<>();
}
