package squareonex.evetracker.commands;

import lombok.Data;

@Data
public class ItemCommand {
    private Long id;
    private String name;
    private Boolean published;
}
