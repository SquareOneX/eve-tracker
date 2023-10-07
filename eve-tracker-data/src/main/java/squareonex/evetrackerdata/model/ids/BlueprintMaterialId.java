package squareonex.evetrackerdata.model.ids;

import jakarta.persistence.*;
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
public class BlueprintMaterialId implements Serializable {
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "blueprint_id", referencedColumnName = "blueprint_id"),
            @JoinColumn(name = "activity_id", referencedColumnName = "activity_id")
    },
            foreignKey = @ForeignKey(name = "fk_blueprintmaterials_blueprints")
    )
    Blueprint blueprint;
    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "material_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_blueprintmaterials_items"))
    Item material;
}
