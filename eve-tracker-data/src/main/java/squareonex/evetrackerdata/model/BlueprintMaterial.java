package squareonex.evetrackerdata.model;

import jakarta.persistence.*;
import lombok.*;
import squareonex.evetrackerdata.model.ids.BlueprintMaterialId;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "blueprint_materials")
public class BlueprintMaterial {
    @EmbeddedId
    private BlueprintMaterialId id = new BlueprintMaterialId();
    private Integer quantity;

    public BlueprintMaterial(Blueprint blueprint, Item material, Integer quantity) {
        this.id = new BlueprintMaterialId(blueprint, material);
        this.quantity = quantity;
    }

    public Blueprint getBlueprint() {
        return this.id.getBlueprint();
    }

    public void setBlueprint(Blueprint blueprint) {
        this.id.setBlueprint(blueprint);
    }

    public Item getMaterial() {
        return this.id.getMaterial();
    }

    public void setMaterial(Item material) {
        this.id.setMaterial(material);
    }
}