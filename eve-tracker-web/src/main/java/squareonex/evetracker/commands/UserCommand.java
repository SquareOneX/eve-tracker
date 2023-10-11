package squareonex.evetracker.commands;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserCommand {
    private Long id;
    private String name;
    private Long playerId;
}
