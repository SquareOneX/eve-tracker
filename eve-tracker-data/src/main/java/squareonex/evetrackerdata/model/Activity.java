package squareonex.evetrackerdata.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Activity {
    @EqualsAndHashCode.Include
    Integer id;
    String name;
}
