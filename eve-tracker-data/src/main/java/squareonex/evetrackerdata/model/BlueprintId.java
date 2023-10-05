package squareonex.evetrackerdata.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@Embeddable
public class BlueprintId implements Serializable {
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "blueprint_id")
    Item itemInfo;
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "activity_id")
    Activity activity;
    public BlueprintId(Item itemInfo, Activity activity) {
        this.itemInfo = itemInfo;
        this.activity = activity;
    }
}
