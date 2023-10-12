package squareonex.evetracker.commands;

import lombok.*;

@Data
public class BlueprintProductCommand {
    private ItemCommand product;
    private Integer quantity;
    private Float probability = 1F;
}
