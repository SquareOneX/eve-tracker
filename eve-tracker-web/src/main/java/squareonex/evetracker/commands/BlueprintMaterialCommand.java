package squareonex.evetracker.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlueprintMaterialCommand {
    private BlueprintCommand blueprintCommand;
    private ItemCommand materialCommand;
    private Integer quantity;
}
