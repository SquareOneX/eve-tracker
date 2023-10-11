package squareonex.evetracker.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ItemCommand {
    private Long id;
    private String name;
    private Boolean published;
    private Set<BlueprintCommand> blueprints;
    private Set<TransactionCommand> transactionCommand;
}
