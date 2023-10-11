package squareonex.evetracker.commands;

import lombok.*;

@Data
public class BlueprintProductCommand {
    private BlueprintCommand blueprintCommand;
    private ItemCommand product;
    private Integer quantity;
    private Float probability;
}
