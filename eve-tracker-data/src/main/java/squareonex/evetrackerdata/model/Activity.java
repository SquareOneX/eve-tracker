package squareonex.evetrackerdata.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Activity {
    @EqualsAndHashCode.Include
    Integer id;
    String name;
}
