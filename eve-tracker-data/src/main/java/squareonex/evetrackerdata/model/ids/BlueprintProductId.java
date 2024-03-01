package squareonex.evetrackerdata.model.ids;

import jakarta.persistence.*;
import lombok.*;
import squareonex.evetrackerdata.model.BlueprintAction;
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
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "blueprint_id", referencedColumnName = "blueprint_id"),
            @JoinColumn(name = "activity_id", referencedColumnName = "activity_id")
    },
            foreignKey = @ForeignKey(name = "fk_blueprintproducts_blueprintactions")
    )
    BlueprintAction blueprint;
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_blueprintproducts_items"))
    Item product;
}
