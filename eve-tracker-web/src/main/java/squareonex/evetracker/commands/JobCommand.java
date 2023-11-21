package squareonex.evetracker.commands;

import lombok.Data;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.BlueprintCopy;

import java.time.LocalDateTime;

@Data
public class JobCommand {
    private Long id;
    private ItemCommand itemCommand;
    private Long quantity;
    private UserCommand userCommand;
    private LocalDateTime startedTime;
    private LocalDateTime finishedTime;
    private Boolean isInternal = false;
    private BlueprintCopy blueprintCopy;
    private Activity activity;
}
