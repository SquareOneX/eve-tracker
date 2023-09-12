package squareonex.evetrackerdata.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseItem {
    @EqualsAndHashCode.Include
    Long id;
    String name;
}
