package squareonex.evetrackerdata.model.ids;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Item;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BlueprintProductId implements Serializable {
    @NonNull
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "blueprint_id", referencedColumnName = "blueprint_id")
    @JoinColumn(name = "activity_id", referencedColumnName = "activity_id")
    Blueprint blueprint;
    @NonNull
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "product_id")
    Item product;
}
