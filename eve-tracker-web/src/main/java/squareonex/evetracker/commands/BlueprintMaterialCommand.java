package squareonex.evetracker.commands;

import lombok.*;

@Data
public class BlueprintMaterialCommand {
    private BlueprintCommand blueprintCommand;
    private ItemCommand materialCommand;
    private Integer quantity;
}
