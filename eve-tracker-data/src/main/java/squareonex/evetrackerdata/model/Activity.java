package squareonex.evetrackerdata.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Activity {
    @EqualsAndHashCode.Include
    Integer id;
    String name;
}
