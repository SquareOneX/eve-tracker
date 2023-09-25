package squareonex.evetrackerdata.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
public class BlueprintKey implements Serializable {
    @EqualsAndHashCode.Include
    Item itemInfo;
    @EqualsAndHashCode.Include
    Activity activity;
    public BlueprintKey(Item itemInfo, Activity activity) {
        this.itemInfo = itemInfo;
        this.activity = activity;
    }
}
