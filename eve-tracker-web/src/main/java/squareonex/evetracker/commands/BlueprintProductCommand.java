package squareonex.evetracker.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlueprintProductCommand {
    private BlueprintCommand blueprintCommand;
    private ItemCommand product;
    private Integer quantity;
    private Float probability;
}
