package squareonex.evetrackerdata.model;

import lombok.*;
import org.springframework.data.repository.cdi.Eager;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(of = {"id", "name", "playerId"})
public class User {
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private Long playerId;
}
