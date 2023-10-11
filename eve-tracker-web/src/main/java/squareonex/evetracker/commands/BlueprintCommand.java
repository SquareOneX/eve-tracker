package squareonex.evetracker.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BlueprintCommand {
    private ActivityCommand activityCommand;
    private ItemCommand itemCommand;
    private Set<BlueprintProductCommand> productCommands = new HashSet<>();
    private Set<BlueprintMaterialCommand> materialCommands = new HashSet<>();
    private Set<BlueprintCopyCommand> copyCommands = new HashSet<>();
    private Set<BlueprintOriginalCommand> originalCommands = new HashSet<>();
    private Duration duration;

    public void setProductCommands(Set<BlueprintProductCommand> productCommands) {
        for (BlueprintProductCommand command : productCommands) {
            command.setBlueprintCommand(this);
        }
        this.productCommands = productCommands;
    }

    public void setMaterialCommands(Set<BlueprintMaterialCommand> materialCommands) {
        for (BlueprintMaterialCommand command : materialCommands) {
            command.setBlueprintCommand(this);
        }
        this.materialCommands = materialCommands;
    }

    public void setCopyCommands(Set<BlueprintCopyCommand> copyCommands) {
        for (BlueprintCopyCommand command : copyCommands) {
            command.setBlueprintCommand(this);
        }
        this.copyCommands = copyCommands;
    }

    public void setOriginalCommands(Set<BlueprintOriginalCommand> originalCommands) {
        for (BlueprintOriginalCommand command : originalCommands) {
            command.setBlueprintCommand(this);
        }
        this.originalCommands = originalCommands;
    }
}
