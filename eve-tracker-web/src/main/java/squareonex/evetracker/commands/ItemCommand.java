package squareonex.evetracker.commands;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString(exclude = {"transactionCommands", "blueprintCommands"})
@EqualsAndHashCode(exclude = {"transactionCommands", "blueprintCommands"})
public class ItemCommand {
    private Long id;
    private String name;
    private Boolean published;
    private Set<BlueprintProductCommand> blueprintCommands = new HashSet<>();
    private Set<TransactionCommand> transactionCommands = new HashSet<>();

    public void setBlueprintCommands(Set<BlueprintProductCommand> blueprintCommands) {
        for (BlueprintProductCommand command : blueprintCommands) {
            command.setProduct(this);
        }
        this.blueprintCommands = blueprintCommands;
    }

    public void setTransactionCommand(Set<TransactionCommand> transactionCommands) {
        for (TransactionCommand command : transactionCommands) {
            command.setItemCommand(this);
        }
        this.transactionCommands = transactionCommands;
    }
}
