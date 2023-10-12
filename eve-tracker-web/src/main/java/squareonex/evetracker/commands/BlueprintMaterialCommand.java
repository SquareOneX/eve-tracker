package squareonex.evetracker.commands;

import lombok.*;

@Data
public class BlueprintMaterialCommand {
    private ItemCommand material;
    private Integer quantity;
}
