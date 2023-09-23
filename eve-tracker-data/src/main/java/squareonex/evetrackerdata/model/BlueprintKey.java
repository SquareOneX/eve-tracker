package squareonex.evetrackerdata.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
public class BlueprintKey{
    @EqualsAndHashCode.Include
    Item item;
    @EqualsAndHashCode.Include
    Activity activity;
    public BlueprintKey(Item item, Activity activity) {
        this.item = item;
        this.activity = activity;
    }
}
