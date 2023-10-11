package squareonex.evetracker.commands;

import lombok.*;

@Data
public class UserCommand {
    private Long id;
    @NonNull private String name;
    private Long playerId;
}
