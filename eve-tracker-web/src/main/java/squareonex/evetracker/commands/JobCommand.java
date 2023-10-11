package squareonex.evetracker.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class JobCommand {
    private Long id;
    private ItemCommand itemCommand;
    private Long quantity;
    private UserCommand userCommand;
    private LocalDateTime startedTime;
    private LocalDateTime finishedTime;
    private Boolean isInternal;
}
