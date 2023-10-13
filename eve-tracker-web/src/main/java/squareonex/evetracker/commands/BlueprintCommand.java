package squareonex.evetracker.commands;

import lombok.*;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Data
public class BlueprintCommand {
    private ActivityCommand activityCommand;
    private ItemCommand itemCommand;
    private Duration duration;
    private Set<BlueprintProductCommand> productCommands = new HashSet<>();
    private Set<BlueprintMaterialCommand> materialCommands = new HashSet<>();
    private Set<BlueprintCopyCommand> copyCommands = new HashSet<>();
    private Set<BlueprintOriginalCommand> originalCommands = new HashSet<>();
}
