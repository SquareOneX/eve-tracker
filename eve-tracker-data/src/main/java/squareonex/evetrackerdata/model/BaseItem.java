package squareonex.evetrackerdata.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class BaseItem {
    @EqualsAndHashCode.Include
    Long id;
    String name;
}
