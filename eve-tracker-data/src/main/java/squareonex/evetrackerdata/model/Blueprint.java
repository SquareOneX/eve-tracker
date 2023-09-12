package squareonex.evetrackerdata.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Blueprint{
    @EqualsAndHashCode.Include
    Item itemInfo;
    @EqualsAndHashCode.Include
    Activity activity;
    Item product;
    Map<Item, Integer> materials;
    Long time;
}
