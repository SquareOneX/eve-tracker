package squareonex.evetracker.commands;

import lombok.Data;

@Data
public class ActivityCommand {
    private Integer id;
    private String name;
    private Boolean published;
}
