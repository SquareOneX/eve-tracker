package squareonex.evetracker.commands;

import lombok.Data;

@Data
public class UserCommand {
    private Long id;
    private String name;
    private Long playerId;
}
