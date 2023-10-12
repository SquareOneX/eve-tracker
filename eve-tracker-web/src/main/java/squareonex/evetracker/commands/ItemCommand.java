package squareonex.evetracker.commands;

import lombok.*;
import squareonex.evetrackerdata.model.Blueprint;

import java.util.HashSet;
import java.util.Set;

@Data
public class ItemCommand {
    private Long id;
    private String name;
    private Boolean published;
}
